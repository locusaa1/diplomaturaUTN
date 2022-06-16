package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.ClientDTO;
import com.utn.diplomaturautn.dataTransferObject.EmployeeDTO;
import com.utn.diplomaturautn.dataTransferObject.LoginDTO;
import com.utn.diplomaturautn.model.Client;
import com.utn.diplomaturautn.model.Employee;
import com.utn.diplomaturautn.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/person")
public class PersonController {

    private final UserService userService;

    private final ClientService clientService;

    private final EmployeeService employeeService;

    private final CityService cityService;

    private final PhoneService phoneService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonController(UserService userService, ClientService clientService, EmployeeService employeeService, CityService cityService, PhoneService phoneService, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.cityService = cityService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.phoneService = phoneService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    private Client fromClientDTOtoClient(ClientDTO clientDTO) {

        return Client.builder().
                city(this.cityService.getById(
                        clientDTO.getCityId())).
                name(clientDTO.getName()).
                lastName(clientDTO.getLastName()).
                dni(clientDTO.getDni()).
                username(clientDTO.getUsername()).
                password(this.passwordEncoder.encode(
                        clientDTO.getPassword())).
                userType(clientDTO.getUserType()).
                phone(this.phoneService.addPhone(
                        this.cityService.getById(clientDTO.getCityId()).getAreaCode(), clientDTO.getPhoneNumber())).
                condition(clientDTO.getCondition()).build();
    }

    private Employee fromEmployeeDTOtoEmployee(EmployeeDTO employeeDTO) {

        return Employee.builder().
                city(this.cityService.getById(employeeDTO.getCityId())).
                name(employeeDTO.getName()).
                lastName(employeeDTO.getLastName()).
                dni(employeeDTO.getDni()).
                username(employeeDTO.getUsername()).
                password(passwordEncoder.encode(employeeDTO.getPassword())).
                userType(employeeDTO.getUserType()).
                condition(employeeDTO.getCondition()).build();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String login(@RequestBody @Valid LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        this.authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = userService.loadUserByUsername(loginDTO.getUsername());
        return jwtService.createToken(userDetails);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Client addClient(@RequestBody @Valid ClientDTO newClientDTO) {

        return this.clientService.addClient(this.fromClientDTOtoClient(newClientDTO));
    }

    @PatchMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Client modifyClient(@RequestBody ClientDTO newClientDTO, @PathVariable("id") int clientId) {

        return this.clientService.modifyClient(this.fromClientDTOtoClient(newClientDTO), clientId);
    }

    @PatchMapping("/client/discontinue/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Client discontinueClient(@PathVariable("id") int clientId) {

        return this.clientService.discontinueClient(clientId);
    }

    @PatchMapping("/client/reactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Client reactivateClient(@PathVariable("id") int clientId) {

        return this.clientService.reactivateClient(clientId);
    }

    @DeleteMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Client deleteClient(@PathVariable("id") int clientId) {

        return this.clientService.deleteClient(clientId);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Employee addEmployee(@RequestBody @Valid EmployeeDTO newEmployeeDTO) {

        return this.employeeService.addEmployee(this.fromEmployeeDTOtoEmployee(newEmployeeDTO));
    }

    @PatchMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Employee modifyEmployee(@RequestBody EmployeeDTO newEmployeeDTO, @PathVariable("id") int employeeId) {

        return this.employeeService.modifyEmployee(this.fromEmployeeDTOtoEmployee(newEmployeeDTO), employeeId);
    }

    @PatchMapping("/employee/reactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Employee reactivateEmployee(@PathVariable("id") int employeeId) {

        return this.employeeService.reactiveEmployee(employeeId);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Employee deleteEmployee(@PathVariable("id") int employeeId) {

        return this.employeeService.deleteEmployee(employeeId);
    }
}
