package com.drmun.graphqldemo.resolver;

import com.drmun.graphqldemo.constants.TestConstant;
import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.error.exception.VehicleNotFoundException;
import com.drmun.graphqldemo.service.VehicleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@GraphQLTest
class VehicleQueryTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    VehicleService vehicleServiceMock;

    private static List <Vehicle> vehicleList;
    private static Optional<Vehicle> vehicle;

    @SneakyThrows
    @BeforeAll
    static void prepareData(){
        final ObjectMapper mapper = new ObjectMapper();
        vehicleList = mapper.readValue(new File("src/test/resources/data.json"), new TypeReference<>() {});
        vehicle = Optional.of(vehicleList.get(0));
    }

    @SneakyThrows
    @Test
    void getVehiclesTest() {
        Mockito.doReturn(vehicleList)
                .when(vehicleServiceMock)
                .getAllVehicles(TestConstant.NUMBER_OF_OBJECT);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/get-vehicles.graphql");
        Assertions.assertThat(response.isOk()).isTrue();
        Assertions.assertThat(response.getList("$.data.vehicles", Vehicle.class))
                .as("Object not found")
                .isNotNull()
                .as("No matches")
                .isEqualTo(vehicleList);
    }

    @SneakyThrows
    @Test
    void getVehicleTest() {
        Mockito.doReturn(vehicle)
                .when(vehicleServiceMock)
                .getVehicle(TestConstant.ID);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/get-vehicle.graphql");
        Assertions.assertThat(response.isOk()).isTrue();
        Assertions.assertThat(response.get("$.data.vehicle.id"))
                .as("Object not found")
                .isNotNull()
                .as("No matches")
                .isEqualTo(TestConstant.FIRST_OBJECT_ID);
    }

    @SneakyThrows
    @Test
    void getNonexistentVehicleTest() {
        Mockito.doReturn(Optional.<Vehicle>empty())
                .when(vehicleServiceMock)
                .getVehicle(TestConstant.NONE_OBJECT);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/get-vehicle.graphql");
        Assertions.assertThat(response.isOk()).isTrue();
        Assertions.assertThat(response.get("$.data.vehicle"))
                .as("The object should not be found.")
                .isNullOrEmpty();

    }

    @SneakyThrows
    @Test
    void getVehicleNotFoundTest() {
        Mockito.doReturn(Optional.empty())
                .when(vehicleServiceMock)
                .getVehicle(TestConstant.NONE_OBJECT);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/not-found-vehicle.graphql");
        Assertions.assertThat(response.isOk()).isTrue();
        List<HashMap> errorList = response.getList("$.errors", HashMap.class);
        HashMap<String, String> errorMessage = errorList.get(0);
        Assertions.assertThat(errorMessage.get("message"))
                .as("Error message no matches")
                .contains(TestConstant.ERROR_MESSAGE);
    }
}