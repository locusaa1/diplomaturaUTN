package com.utn.diplomaturautn.serviceTest.userServiceImplTest;

import com.utn.diplomaturautn.data.DataForModels;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoadByUsernameTest extends UserServiceImplTest {

    @Test
    public void loadByUsername_Successful() {

        UserDetails userFound = DataForModels.newActiveClient2();

        given(this.clientRepository.findByUsernameEquals("Star")).willReturn(Optional.of(userFound));

        UserDetails invocationUserFound = this.userServiceImpl.loadUserByUsername("Star");

        assertThat(invocationUserFound).isEqualTo(userFound);

        verify(this.clientRepository, times(1)).findByUsernameEquals("Star");
    }
}
