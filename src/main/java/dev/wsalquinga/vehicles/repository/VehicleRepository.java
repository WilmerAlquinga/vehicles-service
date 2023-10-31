package dev.wsalquinga.vehicles.repository;

import dev.wsalquinga.vehicles.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author wsalquinga on 31/10/2023
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v " +
            "where (v.deletedAt is null " +
            "or v.deletedAt > current_timestamp)")
    Page<Vehicle> findAllValid(Pageable pageable);

    @Query("SELECT v FROM Vehicle v " +
            "where (v.deletedAt is null " +
            "or v.deletedAt > current_timestamp) " +
            "and v.id = :id")
    Optional<Vehicle> findValidById(Long id);

    @Query("SELECT v FROM Vehicle v " +
            "where (v.deletedAt is null " +
            "or v.deletedAt > current_timestamp) " +
            "and v.purchaseDate between :fromPeriod and :endPeriod")
    List<Vehicle> findAllByPeriod(LocalDate fromPeriod, LocalDate endPeriod);
}
