package com.drmun.graphqldemo.service;

import com.drmun.graphqldemo.dao.entity.Vehicle;

import java.util.List;
import java.util.Optional;

/**
 * Сервис работы с сущностью.
 */
public interface VehicleService {
    /**
     * Получить сущности.
     * @param count Количество.
     * @return Коллекция сущностей.
     */
    List<Vehicle> getAllVehicles(int count);

    /**
     * Получить сущность по идентификатору.
     * @param id Идентификатор сущности.
     * @return Контейнер с сущностью.
     */
    Optional<Vehicle> getVehicle(int id);

    /**
     * Создание сущности.
     * @param type Тип сущности.
     * @param modelCode Код модели.
     * @param brandName Имя бренда.
     * @param launchDate Дата запуска.
     * @return Созданная сущность.
     */
    Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate);
}
