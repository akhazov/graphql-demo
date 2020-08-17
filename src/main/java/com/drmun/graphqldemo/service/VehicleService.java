package com.drmun.graphqldemo.service;

import com.drmun.graphqldemo.dao.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicles(int count);

    Optional<Vehicle> getVehicle(int id);

    Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate);

}
