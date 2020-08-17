package com.drmun.graphqldemo.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Обработчик запросов сущностей.
 */
@Component
public class VehicleQuery implements GraphQLQueryResolver {
    @Autowired
    private VehicleService vehicleService;

    /**
     * Запрос списка сущностей.
     * @param count Количество сущностей.
     * @return Коллекция сущностей.
     */
    public List<Vehicle> getVehicles(int count) {
        return this.vehicleService.getAllVehicles(count);
    }

    /**
     * Запрос сущности по идентификатору.
     * @param id Идентификатор сущности.
     * @return Резльтат запроса.
     */
    public Optional<Vehicle> getVehicle(int id) {
        return this.vehicleService.getVehicle(id);
    }
}
