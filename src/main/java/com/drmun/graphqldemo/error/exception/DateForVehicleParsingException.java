package com.drmun.graphqldemo.error.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Исключение в случае некорректного формата даты объекта во входном параметре.
 */
@Slf4j
public class DateForVehicleParsingException extends RuntimeException implements GraphQLError {
    private static final String MESSAGE = "The resulting launchDate = %s does not match the date pattern: 2016-08-16. " +
            "Check passed parameters";
    public DateForVehicleParsingException(String message) {
        super(String.format(MESSAGE, message));
        log.error(this.getMessage());
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
