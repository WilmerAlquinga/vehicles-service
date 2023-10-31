package dev.wsalquinga.vehicles.service.impl;

import dev.wsalquinga.vehicles.dto.req.VehicleReqDTO;
import dev.wsalquinga.vehicles.dto.res.VehicleResDTO;
import dev.wsalquinga.vehicles.entity.Vehicle;
import dev.wsalquinga.vehicles.mapper.VehicleMapper;
import dev.wsalquinga.vehicles.repository.VehicleRepository;
import dev.wsalquinga.vehicles.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author wsalquinga on 31/10/2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public Page<VehicleResDTO> findAll(Pageable pageable) {
        return this.vehicleRepository.findAllValid(pageable).map(this.vehicleMapper::toVehicleResDTO);
    }

    @Override
    public VehicleResDTO create(VehicleReqDTO vehicleReqDTO) {
        return null;
    }
}
