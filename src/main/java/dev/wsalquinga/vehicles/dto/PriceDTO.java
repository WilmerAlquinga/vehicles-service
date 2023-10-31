package dev.wsalquinga.vehicles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author wsalquinga on 31/10/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {
    private String placa;
    private BigDecimal precio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDTO priceDTO = (PriceDTO) o;
        return Objects.equals(placa, priceDTO.placa) && Objects.equals(precio, priceDTO.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, precio);
    }
}
