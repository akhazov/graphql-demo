package com.drmun.graphqldemo.error;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Обработчик ошибок для вывода в ответе только информации по ошибке
 */
@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream().map(this::getNested).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            final Throwable exception = ((ExceptionWhileDataFetching) error).getException();
            if (exception instanceof GraphQLError) {
                return (GraphQLError) exception;
            }
        }
    return error;
    }
}
