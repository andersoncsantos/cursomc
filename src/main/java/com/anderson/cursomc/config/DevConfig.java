package com.anderson.cursomc.config;

import com.anderson.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hibernateStrategy;

    @Bean
    public boolean instantiateDataBase() throws ParseException {

        if(!"create".equals(hibernateStrategy)){
            return false;
        }

        dbService.instantiateTestDatabase();

        return true;
    }
}
