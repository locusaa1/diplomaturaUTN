package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.utn.diplomaturautn.dataTransferObject.EmployeeResponseDTO;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("employee")
@Entity
@Table(name = "employees")
public class Employee extends User implements UserDetails {

    @Column(name = "employee_condition")
    @Enumerated(value = EnumType.STRING)
    private EmployeeCondition condition;

    @Builder
    public Employee(int id, City city, String name, String lastName, String dni, String username, String password, UserType userType, EmployeeCondition condition) {

        this.setId(id);
        this.setCity(city);
        this.setName(name);
        this.setLastName(lastName);
        this.setDni(dni);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserType(userType);
        this.condition = condition;
    }

    public Employee modifyUsingEmployee(Employee newData) {

        this.modifyUsingUser(newData);
        return this;
    }

    public EmployeeResponseDTO fromEmployeeToResponseDTO() {

        return new EmployeeResponseDTO(
                this.getName(),
                this.getLastName(),
                this.getDni(),
                this.getCity().getName(),
                this.getUsername(),
                this.getCondition(),
                this.getUserType());
    }

    public static List<EmployeeResponseDTO> fromEmployeeListToResponse(List<Employee> employees) {

        List<EmployeeResponseDTO> employeesResponse = new ArrayList<>();

        for (Employee e : employees) {

            employeesResponse.add(e.fromEmployeeToResponseDTO());
        }
        return employeesResponse;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getUserType().toString()));
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return (this.getCondition() == EmployeeCondition.ACTIVE);
    }

    @Override
    public boolean equals(Object e) {

        if (e instanceof Employee) {

            Employee emp = (Employee) e;

            return this.getName().equals(emp.getName())
                    && this.getLastName().equals(emp.getLastName())
                    && this.getDni().equals(emp.getDni())
                    && this.getCity().equals(emp.getCity())
                    && this.getUsername().equals(emp.getUsername())
                    && this.getUserType().equals(emp.getUserType())
                    && this.getCondition().equals(emp.getCondition());
        } else {
            return false;
        }
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
