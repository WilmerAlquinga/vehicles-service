package dev.wsalquinga.vehicles.controller;

import dev.wsalquinga.vehicles.common.GlobalConstant;
import dev.wsalquinga.vehicles.dto.req.VehicleReqDTO;
import dev.wsalquinga.vehicles.dto.res.VehicleResDTO;
import dev.wsalquinga.vehicles.service.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wsalquinga on 31/10/2023
 */
@AllArgsConstructor
@RestController
@CrossOrigin("${frontend.base.url}")
@RequestMapping(GlobalConstant.API_V1_VERSION + "/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Page<VehicleResDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(this.vehicleService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<VehicleResDTO> create(@Valid @RequestBody VehicleReqDTO vehicleReqDTO) {
        return new ResponseEntity<>(this.vehicleService.create(vehicleReqDTO), HttpStatus.CREATED);
    }
}
