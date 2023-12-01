package dev.wsalquinga.vehicles.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.wsalquinga.vehicles.common.GlobalConstant;
import dev.wsalquinga.vehicles.config.WebClientConfig;
import dev.wsalquinga.vehicles.dto.PriceDTO;
import dev.wsalquinga.vehicles.dto.req.VehicleReqDTO;
import dev.wsalquinga.vehicles.dto.res.VehicleResDTO;
import dev.wsalquinga.vehicles.entity.Vehicle;
import dev.wsalquinga.vehicles.exception.ResourceNotFoundException;
import dev.wsalquinga.vehicles.exception.ServerErrorException;
import dev.wsalquinga.vehicles.mapper.VehicleMapper;
import dev.wsalquinga.vehicles.repository.VehicleRepository;
import dev.wsalquinga.vehicles.service.VehicleService;
import dev.wsalquinga.vehicles.util.HolidaysUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wsalquinga on 31/10/2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final WebClientConfig webClient;

    @Override
    public Page<VehicleResDTO> findAll(Pageable pageable) {
        return this.vehicleRepository.findAllValid(pageable).map(this.vehicleMapper::toVehicleResDTO);
    }

    @Override
    @Transactional
    public VehicleResDTO create(VehicleReqDTO vehicleReqDTO) {
        Vehicle vehicle = this.vehicleMapper.toVehicleEntity(vehicleReqDTO);
        PriceDTO vehiclePrice = this.getPriceByLicensePlate(vehicleReqDTO.getPlate());
        vehicle.setPrice(vehiclePrice.getPrecio());
        return this.vehicleMapper.toVehicleResDTO(this.vehicleRepository.save(vehicle));
    }

    @Override
    public List<VehicleResDTO> findForMaintenance(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || HolidaysUtil.HOLIDAYS_LIST.contains(date))
            return new ArrayList<>();
        LocalDate startPeriod = date.minusDays(GlobalConstant.PERIOD_MAINTENANCE);
        LocalDate endPeriod = date.minusDays(1);
        List<Vehicle> vehiclesInLastSixtyDays = this.vehicleRepository.findAllByPeriod(startPeriod, endPeriod);
        List<Vehicle> vehiclesForMaintenance = vehiclesInLastSixtyDays.stream()
                .filter(vehicle -> {
                    DayOfWeek dayOfPurchase = vehicle.getPurchaseDate().getDayOfWeek();
                    if (dayOfPurchase == day
                    || day == DayOfWeek.MONDAY && (dayOfPurchase == DayOfWeek.SATURDAY || dayOfPurchase == DayOfWeek.SUNDAY)) {
                        return true;
                    } else if (HolidaysUtil.HOLIDAYS_LIST.contains(vehicle.getPurchaseDate())) {
                        LocalDate weekDay = vehicle.getPurchaseDate();
                        while(HolidaysUtil.HOLIDAYS_LIST.contains(weekDay)
                                || (weekDay.getDayOfWeek() == DayOfWeek.SATURDAY || weekDay.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                            weekDay = weekDay.plusDays(1);
                        }
                        return weekDay.getDayOfWeek() == day;
                    }
                    return false;
                })
                .toList();
        return this.vehicleMapper.toVehicleResDTO(vehiclesForMaintenance);
    }

    private PriceDTO getPriceByLicensePlate(String plate) {
        String vehiclePrice = this.webClient.webClient()
                .get()
                .uri("?placa=" + plate)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    throw new ServerErrorException("El servicio de consultas de precios no está disponible");
                })
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    throw new ResourceNotFoundException("No hay un precio asignado para la placa: " + plate);
                })
                .bodyToMono(String.class)
                .block();
        PriceDTO priceDTO;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            priceDTO = objectMapper.readValue(vehiclePrice, PriceDTO.class);
        } catch (JsonProcessingException e) {
            throw new ServerErrorException("Error al procesar el precio del vehículo");
        }
        return priceDTO;
    }
}
