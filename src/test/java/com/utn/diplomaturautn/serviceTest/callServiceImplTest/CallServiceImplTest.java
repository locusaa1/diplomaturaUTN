package com.utn.diplomaturautn.serviceTest.callServiceImplTest;

import com.utn.diplomaturautn.repositroy.CallRepository;
import com.utn.diplomaturautn.service.impl.CallServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CallServiceImplTest {

    @Mock
    protected CallRepository callRepository;

    @InjectMocks
    protected CallServiceImpl callServiceImpl;
}
