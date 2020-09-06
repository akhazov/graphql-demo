package com.drmun.graphqldemo.resolver;

import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.error.exception.VehicleNotFoundException;
import com.drmun.graphqldemo.service.VehicleService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Обработчик запросов сущностей.
 */
@Component
public class VehicleQuery implements GraphQLQueryResolver {
    private final VehicleService vehicleService;

    public VehicleQuery(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Запрос списка сущностей.
     * @param count Количество сущностей.
     * @return Коллекция сущностей.
     */
    public List<Vehicle> getVehicles(int count) {
        return vehicleService.getAllVehicles(count);
    }

    /**
     * Запрос сущности по идентификатору.
     * @param id Идентификатор сущности.
     * @return Результат запроса или исключение об ошибке, что объект с таким ID не найден.
     */
    public Vehicle getVehicle(int id) {
        return vehicleService.getVehicle(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }
}
