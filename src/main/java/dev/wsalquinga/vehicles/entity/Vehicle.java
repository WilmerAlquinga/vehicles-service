package dev.wsalquinga.vehicles.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author wsalquinga on 31/10/2023
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "vehicle", schema = "vehicles")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "vehicle_plate")
    private String plate;

    @Column(name = "vehicle_model")
    private String model;

    @Column(name = "vehicle_year")
    private Integer year;

    @Column(name = "vehicle_purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "vehicle_price")
    private BigDecimal price;

    @Column(name = "vehicle_observation")
    private String observation;

    @CreatedDate
    @Column(name = "vehicle_created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "vehicle_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "vehicle_deleted_at")
    private LocalDateTime deletedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
