package com.drmun.graphqldemo.service;

import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.dao.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> getAllVehicles(int count) {
        return vehicleRepository.findAll()
                .stream()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicle> getVehicle(int id) {
        return vehicleRepository.findById(id);
    }

    @Override
    @Transactional
    public Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));
        return vehicleRepository.save(vehicle);
    }
}
