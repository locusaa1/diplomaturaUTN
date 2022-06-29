package com.utn.diplomaturautn.data;

import com.utn.diplomaturautn.dataTransferObject.CallDTO;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.model.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
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

    public static Bill newBill() {

        return Bill.builder()
                .id(1)
                .client(DataForModels.newActiveClient2())
                .callsSum(2)
                .total(30.00)
                .generatedDate(Date.valueOf(LocalDate.of(2020, 1, 20)))
                .expirationDate(Date.valueOf(LocalDate.of(2020, 1, 30))).build();
    }

    public static List<Phone> phoneList() {

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(DataForModels.newPhone());
        phoneList.add(DataForModels.newPhone());
        phoneList.add(DataForModels.newPhone());
        return phoneList;
    }

    public static List<Bill> billList() {

        List<Bill> billList = new ArrayList<>();
        billList.add(DataForModels.newBill());
        billList.add(DataForModels.newBill());
        billList.add(DataForModels.newBill());
        return billList;
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

    public static Phone savePhone() {

        return Phone.builder()
                .id(0)
                .number("2235012248")
                .build();
    }

    public static Phone newPhone() {

        return Phone.builder()
                .id(1)
                .number("2235012248")
                .build();
    }

    public static Phone newPhone2() {

        return Phone.builder()
                .id(2)
                .number("2235012248")
                .build();
    }

    public static CallDTO newCallDTO() {

        return CallDTO.builder()
                .originPhone("2915012247")
                .destinationPhone("2915012247")
                .duration(50)
                .dateTime("2022-05-20 12:00:00").build();
    }

    public static Call newCall() {

        return Call.builder()
                .id(0)
                .originPhone(DataForModels.newPhone())
                .destinationPhone(DataForModels.newPhone2())
                .startDate(Timestamp.valueOf("2022-05-20 23:05:00"))
                .duration(50).build();
    }

    public static Call newCallUpdatedId() {

        return Call.builder()
                .id(1)
                .originCity(DataForModels.newCity())
                .destinationCity(DataForModels.newCity2())
                .originPhone(DataForModels.newPhone())
                .destinationPhone(DataForModels.newPhone2())
                .startDate(Timestamp.valueOf("2022-05-20 23:05:00"))
                .fee(DataForModels.newFee())
                .total(DataForModels.newFee().getCost() * 50)
                .duration(50).build();
    }

    public static Call newCall2() {

        return Call.builder()
                .id(2)
                .originCity(DataForModels.newCity())
                .destinationCity(DataForModels.newCity2())
                .originPhone(DataForModels.newPhone())
                .destinationPhone(DataForModels.newPhone2())
                .startDate(Timestamp.valueOf("2022-05-10 23:05:00"))
                .fee(DataForModels.newFee())
                .total(DataForModels.newFee().getCost() * 50)
                .duration(50).build();
    }

    public static List<Call> callList() {

        List<Call> calls = new ArrayList<>();
        calls.add(DataForModels.newCallUpdatedId());
        calls.add(DataForModels.newCall2());
        return calls;
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

    public static UserDetails clientAuthentication() {

        return new User("Leonardo",
                "123",
                List.of(new SimpleGrantedAuthority(UserType.CLIENT.toString())));
    }

    public static UserDetails employeeAuthentication() {

        return new User("Leonardo",
                "123",
                List.of(new SimpleGrantedAuthority(UserType.EMPLOYEE.toString())));
    }

    public static String dateOne() {

        return "2022-01-10";
    }

    public static Timestamp timestampOne() {

        return Timestamp.valueOf(DataForModels.dateOne() + " 00:00:00");
    }

    public static String dateTwo() {

        return "2022-05-30";
    }

    public static Timestamp timestampTwo() {

        return Timestamp.valueOf(DataForModels.dateTwo() + " 23:59:59");
    }

    public static String dateThree() {

        return "2022-12-01";
    }

    public static Timestamp timestampThree() {

        return Timestamp.valueOf(DataForModels.dateThree());
    }

    public static String dateFour() {

        return "2022-01-01";
    }

    public static Timestamp timestampFour() {

        return Timestamp.valueOf(DataForModels.dateFour());
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
                .phone(DataForModels.newPhone2())
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
                .phone(DataForModels.newPhone2())
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
                .userType(UserType.EMPLOYEE)
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
                .userType(UserType.EMPLOYEE)
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
                .userType(UserType.EMPLOYEE)
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
