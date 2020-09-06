package com.drmun.graphqldemo.error.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

/**
 * Кастомное исключение, если объект не найден в базе данных.
 */
public class VehicleNotFoundException extends RuntimeException implements GraphQLError {
    private static final String MESSAGE = "Vehicle ID = %d not found";
    public VehicleNotFoundException(int id) {
        super(String.format(MESSAGE, id));
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return null;
    }
}
