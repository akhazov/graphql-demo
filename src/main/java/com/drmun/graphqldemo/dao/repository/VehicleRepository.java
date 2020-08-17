package com.drmun.graphqldemo.dao.repository;

import com.drmun.graphqldemo.dao.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с транспортными средствами.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
