package com.utn.diplomaturautn.controller;

import com.utn.diplomaturautn.dataTransferObject.*;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.EmployeeCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import com.utn.diplomaturautn.exception.ErrorSavingEntityException;
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
import java.util.List;

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
                userType(UserType.CLIENT).
                phone(this.phoneService.addPhone(
                        this.cityService.getById(clientDTO.getCityId()).getAreaCode(), clientDTO.getPhoneNumber())).
                condition(ClientCondition.ACTIVE).build();
    }

    private Employee fromEmployeeDTOtoEmployee(EmployeeDTO employeeDTO) {

        return Employee.builder().
                city(this.cityService.getById(employeeDTO.getCityId())).
                name(employeeDTO.getName()).
                lastName(employeeDTO.getLastName()).
                dni(employeeDTO.getDni()).
                username(employeeDTO.getUsername()).
                password(passwordEncoder.encode(employeeDTO.getPassword())).
                userType(UserType.EMPLOYEE).
                condition(EmployeeCondition.ACTIVE).build();
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
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ClientResponseDTO addClient(@RequestBody @Valid ClientDTO newClientDTO) {

        try {

            return this.clientService.addClient(this.fromClientDTOtoClient(newClientDTO)).fromClientToResponseDTO();
        } catch (ErrorSavingEntityException exception) {
            this.phoneService.deletePhoneByNumber(newClientDTO.getPhoneNumber());
            throw exception;
        }
    }

    @GetMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ClientResponseDTO> getAllClients() {

        return Client.fromClientListToResponseDTO(this.clientService.getAll());
    }

    @GetMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientResponseDTO getClientById(@PathVariable("id") int clientId) {

        return this.clientService.getById(clientId).fromClientToResponseDTO();
    }

    @PatchMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientResponseDTO modifyClient(@RequestBody ClientDTO newClientDTO, @PathVariable("id") int clientId) {

        return this.clientService.modifyClient(this.fromClientDTOtoClient(newClientDTO), clientId).fromClientToResponseDTO();
    }

    @PatchMapping("/client/discontinue/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientResponseDTO discontinueClient(@PathVariable("id") int clientId) {

        return this.clientService.discontinueClient(clientId).fromClientToResponseDTO();
    }

    @PatchMapping("/client/reactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientResponseDTO reactivateClient(@PathVariable("id") int clientId) {

        return this.clientService.reactivateClient(clientId).fromClientToResponseDTO();
    }

    @DeleteMapping("/client/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ClientResponseDTO deleteClient(@PathVariable("id") int clientId) {

        return this.clientService.deleteClient(clientId).fromClientToResponseDTO();
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeeResponseDTO addEmployee(@RequestBody @Valid EmployeeDTO newEmployeeDTO) {

        return this.employeeService.addEmployee(this.fromEmployeeDTOtoEmployee(newEmployeeDTO)).fromEmployeeToResponseDTO();
    }

    @GetMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<EmployeeResponseDTO> getAllEmployees() {

        return Employee.fromEmployeeListToResponse(this.employeeService.getAll());
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeResponseDTO getEmployeeById(@PathVariable("id") int employeeId) {

        return this.employeeService.getById(employeeId).fromEmployeeToResponseDTO();
    }

    @PatchMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeResponseDTO modifyEmployee(@RequestBody EmployeeDTO newEmployeeDTO, @PathVariable("id") int employeeId) {

        return this.employeeService.modifyEmployee(this.fromEmployeeDTOtoEmployee(newEmployeeDTO), employeeId).fromEmployeeToResponseDTO();
    }

    @PatchMapping("/employee/reactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeResponseDTO reactivateEmployee(@PathVariable("id") int employeeId) {

        return this.employeeService.reactiveEmployee(employeeId).fromEmployeeToResponseDTO();
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeResponseDTO deleteEmployee(@PathVariable("id") int employeeId) {

        return this.employeeService.deleteEmployee(employeeId).fromEmployeeToResponseDTO();
    }
}
