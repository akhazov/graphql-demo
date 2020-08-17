package com.drmun.graphqldemo.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Распознаватель мутаций.
 */
@Component
public class VehicleMutations implements GraphQLMutationResolver {
    @Autowired
    private VehicleService vehicleService;

    public Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}
