package com.utn.diplomaturautn.serviceTest.billServiceImplTest;

import com.utn.diplomaturautn.repositroy.BillRepository;
import com.utn.diplomaturautn.service.impl.BillServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BillServiceImplTest {

    @Mock
    protected BillRepository billRepository;

    @InjectMocks
    protected BillServiceImpl billServiceImpl;
}
