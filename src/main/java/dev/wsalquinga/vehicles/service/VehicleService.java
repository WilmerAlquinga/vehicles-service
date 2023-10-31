package dev.wsalquinga.vehicles.service;

import dev.wsalquinga.vehicles.dto.req.VehicleReqDTO;
import dev.wsalquinga.vehicles.dto.res.VehicleResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author wsalquinga on 31/10/2023
 */
public interface VehicleService {
    Page<VehicleResDTO> findAll(Pageable pageable);

    VehicleResDTO create(VehicleReqDTO vehicleReqDTO);
}
