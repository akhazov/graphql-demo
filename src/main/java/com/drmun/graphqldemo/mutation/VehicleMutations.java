package com.drmun.graphqldemo.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.service.VehicleService;
import org.springframework.stereotype.Component;

/**
 * Распознаватель мутаций.
 */
@Component
public class VehicleMutations implements GraphQLMutationResolver {
    private final VehicleService vehicleService;

    public VehicleMutations(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Создание сущности.
     * @param type Тип.
     * @param modelCode Код модели.
     * @param brandName Имя бренда.
     * @param launchDate Дата запуска.
     * @return Созданная сущность.
     */
    public Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate) {
        return vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}
