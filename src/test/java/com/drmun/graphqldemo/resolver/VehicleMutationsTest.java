package com.drmun.graphqldemo.resolver;

import com.drmun.graphqldemo.constants.TestConstant;
import com.drmun.graphqldemo.dao.entity.Vehicle;
import com.drmun.graphqldemo.service.VehicleService;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@GraphQLTest
class VehicleMutationsTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    VehicleService vehicleServiceMock;

    static Vehicle vehicle = new Vehicle();

    @BeforeAll
    static void setUp() {
        vehicle.setId(TestConstant.ID);
        vehicle.setType(TestConstant.TYPE);
        vehicle.setModelCode(TestConstant.MODEL_CODE);
        vehicle.setBrandName(TestConstant.BRAND_NAME);
        vehicle.setLaunchDate(TestConstant.LAUNCH_DATE);
    }

    @Test()
    void createVehicleTest() throws IOException {
        doReturn(vehicle)
                .when(vehicleServiceMock)
                .createVehicle(TestConstant.TYPE, TestConstant.MODEL_CODE, TestConstant.BRAND_NAME, TestConstant.LAUNCH_DATE);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/create-vehicle.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.createVehicle.id")).as("Id not found").isNotNull();
        assertThat(response.get("$.data.createVehicle.type"))
                .as("Type not found").isNotNull()
                .as("The data does not match").isEqualTo(TestConstant.TYPE);
    }
}
