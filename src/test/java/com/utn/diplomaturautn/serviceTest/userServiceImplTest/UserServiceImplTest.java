package com.utn.diplomaturautn.serviceTest.userServiceImplTest;

import com.utn.diplomaturautn.repositroy.ClientRepository;
import com.utn.diplomaturautn.repositroy.EmployeeRepository;
import com.utn.diplomaturautn.service.impl.UserServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    protected ClientRepository clientRepository;

    @Mock
    protected EmployeeRepository employeeRepository;

    @InjectMocks
    protected UserServiceImpl userServiceImpl;
}
