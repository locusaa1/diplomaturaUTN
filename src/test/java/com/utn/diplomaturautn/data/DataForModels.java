package com.utn.diplomaturautn.data;

import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.model.*;
import org.springframework.security.test.context.support.WithMockUser;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class DataForModels {

    public static Province newProvince() {

        return Province.builder()
                .id(1)
                .name("Buenos Aires").build();
    }

    public static City newCity() {

        return City.builder()
                .id(1)
                .province(DataForModels.newProvince())
                .name("Mar del Plata").build();
    }

    public static City newCity2() {

        return City.builder()
                .id(2)
                .province(DataForModels.newProvince())
                .name("Tandil").build();
    }

    public static List<City> cityList() {

        List<City> cityList = new ArrayList<>();
        cityList.add(newCity());
        cityList.add(newCity());
        cityList.add(newCity());
        return cityList;
    }

    public static Fee newFee() {

        return Fee.builder()
                .id(1)
                .originCity(DataForModels.newCity())
                .destinationCity(DataForModels.newCity2())
                .cost(22.20)
                .startTime(Time.valueOf("00:00:00"))
                .endTime(Time.valueOf("23:59:59")).build();
    }

    public static List<Fee> feeList() {

        List<Fee> feeList = new ArrayList<>();
        feeList.add(DataForModels.newFee());
        feeList.add(DataForModels.newFee());
        feeList.add(DataForModels.newFee());
        return feeList;
    }

    public static Phone newPhone() {

        return Phone.builder()
                .id(1)
                .number("2235012248")
                .build();
    }

    public static Client newActiveClient() {

        return Client.builder()
                .name("Leonardo")
                .lastName("Gazaba")
                .dni("40635728")
                .username("Locusa")
                .password("123")
                .userType(UserType.CLIENT)
                .condition(ClientCondition.ACTIVE)
                .city(DataForModels.newCity())
                .phone(DataForModels.newPhone())
                .build();
    }

    public static Client newActiveClient2() {

        return Client.builder()
                .id(2)
                .name("Jose")
                .lastName("Perez")
                .dni("40635727")
                .username("Star")
                .password("123")
                .userType(UserType.CLIENT)
                .condition(ClientCondition.ACTIVE)
                .city(DataForModels.newCity())
                .phone(DataForModels.newPhone())
                .build();
    }

    public static Client newActiveClient2Copy() {

        return Client.builder()
                .id(2)
                .name("Jose")
                .lastName("Perez")
                .dni("40635727")
                .username("Star")
                .password("123")
                .userType(UserType.CLIENT)
                .condition(ClientCondition.ACTIVE)
                .city(DataForModels.newCity())
                .phone(DataForModels.newPhone())
                .build();
    }

    public static Client newClientIdUpdated() {

        Client newClient = DataForModels.newActiveClient();
        newClient.setId(1);
        return newClient;
    }

    public static List<Client> clientList() {

        List<Client> clientList = new ArrayList<>();
        clientList.add(DataForModels.newActiveClient());
        clientList.add(DataForModels.newActiveClient2());
        return clientList;
    }

    public static List<Client> clientEmptyList() {

        return new ArrayList<Client>();
    }

    public static Employee newActiveEmployee() {

        return Employee.builder()
                .name("Leonardo")
                .lastName("Gazaba")
                .dni("40635728")
                .username("Locusa")
                .password("123")
                .userType(UserType.CLIENT)
                .condition(EmployeeCondition.ACTIVE)
                .city(DataForModels.newCity())
                .build();
    }

    public static Employee newActiveEmployee2() {

        return Employee.builder()
                .id(2)
                .name("Jose")
                .lastName("Perez")
                .dni("40635727")
                .username("Star")
                .password("123")
                .userType(UserType.CLIENT)
                .condition(EmployeeCondition.ACTIVE)
                .city(DataForModels.newCity())
                .build();
    }

    public static Employee newActiveEmployee2Copy() {

        return Employee.builder()
                .id(2)
                .name("Jose")
                .lastName("Perez")
                .dni("40635727")
                .username("Star")
                .password("123")
                .userType(UserType.CLIENT)
                .condition(EmployeeCondition.ACTIVE)
                .city(DataForModels.newCity())
                .build();
    }

    public static Employee newEmployeeIdUpdated() {

        Employee newEmployee = DataForModels.newActiveEmployee();
        newEmployee.setId(1);
        return newEmployee;
    }

    public static List<Employee> employeeList() {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(DataForModels.newActiveEmployee());
        employeeList.add(DataForModels.newActiveEmployee2());
        return employeeList;
    }

    public static List<Employee> employeeEmptyList() {

        return new ArrayList<Employee>();
    }

}
