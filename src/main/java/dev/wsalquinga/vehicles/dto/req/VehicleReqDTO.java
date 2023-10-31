package dev.wsalquinga.vehicles.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author wsalquinga on 31/10/2023
 */
@Data
public class VehicleReqDTO {
    @NotBlank(message = "La placa es requerida")
    private String plate;

    @NotBlank(message = "El modelo del vehiculo es requerida")
    private String model;

    @NotNull(message = "El año es requerido")
    private Integer year;

    @NotNull(message = "La fecha de compra es requerida")
    private LocalDate purchaseDate;

    @NotNull(message = "El precio es requerido")
    private BigDecimal price;
}
