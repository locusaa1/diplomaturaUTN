package com.utn.diplomaturautn.serviceTest.cityServiceImplTest;

import com.utn.diplomaturautn.repositroy.CityRepository;
import com.utn.diplomaturautn.service.impl.CityServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    @Mock
    protected CityRepository cityRepository;

    @InjectMocks
    protected CityServiceImpl cityServiceImpl;
}
