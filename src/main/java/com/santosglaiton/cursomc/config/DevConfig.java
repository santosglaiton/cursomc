package com.santosglaiton.cursomc.config;

import com.santosglaiton.cursomc.service.DBService;
import com.santosglaiton.cursomc.service.EmailService;
import com.santosglaiton.cursomc.service.SmtpEmailService;
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
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabse() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }

        dbService.instantiateTestDatabase();

        return true;
    }


}
