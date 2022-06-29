package com.utn.diplomaturautn.serviceTest.phoneServiceImplTest;

import com.utn.diplomaturautn.repositroy.PhoneRepository;
import com.utn.diplomaturautn.service.impl.PhoneServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceImplTest {

    @Mock
    protected PhoneRepository phoneRepository;

    @InjectMocks
    protected PhoneServiceImpl phoneServiceImpl;
}
