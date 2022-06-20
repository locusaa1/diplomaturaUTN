package com.utn.diplomaturautn.serviceTest.employeeServiceImplTest;

import com.utn.diplomaturautn.repositroy.EmployeeRepository;
import com.utn.diplomaturautn.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    protected EmployeeRepository employeeRepository;

    @InjectMocks
    protected EmployeeServiceImpl employeeServiceImpl;
}
