package dev.wsalquinga.vehicles.mapper;

import dev.wsalquinga.vehicles.dto.req.VehicleReqDTO;
import dev.wsalquinga.vehicles.dto.res.VehicleResDTO;
import dev.wsalquinga.vehicles.entity.Vehicle;
import org.mapstruct.Mapper;

/**
 * @author wsalquinga on 31/10/2023
 */
@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleResDTO toVehicleResDTO(Vehicle vehicle);

    Vehicle toVehicleEntity(VehicleReqDTO vehicleReqDTO);
}
