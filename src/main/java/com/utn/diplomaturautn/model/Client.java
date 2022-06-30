package com.utn.diplomaturautn.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.utn.diplomaturautn.dataTransferObject.ClientResponseDTO;
import com.utn.diplomaturautn.enumerated.ClientCondition;
import com.utn.diplomaturautn.enumerated.UserType;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("client")
@Entity
@Table(name = "clients")
public class Client extends User implements UserDetails {

    @OneToOne
    @JoinColumn(name = "id_phone")
    private Phone phone;

    @Column(name = "client_condition")
    @Enumerated(value = EnumType.STRING)
    private ClientCondition condition;

    @Builder
    public Client(int id, City city, String name, String lastName, String dni, String username, String password, UserType userType, Phone phone, ClientCondition condition) {

        this.setId(id);
        this.setCity(city);
        this.setName(name);
        this.setLastName(lastName);
        this.setDni(dni);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserType(userType);
        this.phone = phone;
        this.condition = condition;
    }

    public Client modifyUsingClient(Client newData) {

        this.modifyUsingUser(newData);
        if (!Objects.isNull(newData.getPhone()) && !this.getPhone().equals(newData.getPhone())) {

            this.setPhone(newData.getPhone());
        }
        return this;
    }

    public ClientResponseDTO fromClientToResponseDTO() {

        return new ClientResponseDTO(
                this.getId(),
                this.getName(),
                this.getLastName(),
                this.getDni(),
                this.getCity().getName(),
                this.getPhone().getNumber(),
                this.getUsername(),
                this.getCondition(),
                this.getUserType());
    }

    public static List<ClientResponseDTO> fromClientListToResponseDTO(List<Client> clients) {

        List<ClientResponseDTO> clientsResponse = new ArrayList<>();

        for (Client c : clients) {

            clientsResponse.add(c.fromClientToResponseDTO());
        }
        return clientsResponse;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getUserType().toString()));
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return (this.getCondition() == ClientCondition.ACTIVE);
    }

    @Override
    public boolean equals(Object c) {

        if (c instanceof Client) {

            Client cli = (Client) c;

            return this.getName().equals(cli.getName())
                    && this.getLastName().equals(cli.getLastName())
                    && this.getDni().equals(cli.getDni())
                    && this.getCity().equals(cli.getCity())
                    && this.getUsername().equals(cli.getUsername())
                    && this.getUserType().equals(cli.getUserType())
                    && this.getPhone().equals(cli.phone)
                    && this.getCondition().equals(cli.getCondition());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {

        return getClass().hashCode();
    }
}
