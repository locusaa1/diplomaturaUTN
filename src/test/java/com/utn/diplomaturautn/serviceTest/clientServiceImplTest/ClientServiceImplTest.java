package com.utn.diplomaturautn.serviceTest.clientServiceImplTest;


import com.utn.diplomaturautn.repositroy.ClientRepository;
import com.utn.diplomaturautn.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//mvn clean test jacoco:report

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    protected ClientRepository clientRepository;

    @InjectMocks
    protected ClientServiceImpl clientServiceImpl;
}
