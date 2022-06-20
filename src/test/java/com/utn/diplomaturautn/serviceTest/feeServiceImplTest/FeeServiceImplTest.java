package com.utn.diplomaturautn.serviceTest.feeServiceImplTest;

import com.utn.diplomaturautn.repositroy.FeeRepository;
import com.utn.diplomaturautn.service.impl.FeeServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FeeServiceImplTest {

    @Mock
    protected FeeRepository feeRepository;

    @InjectMocks
    protected FeeServiceImpl feeServiceImpl;
}
