package com.utn.diplomaturautn;

import com.utn.diplomaturautn.repositroy.impl.CustomRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class DiplomaturaUtnApplication {

    public static void main(String[] args) {

        SpringApplication.run(DiplomaturaUtnApplication.class, args);
    }
}
