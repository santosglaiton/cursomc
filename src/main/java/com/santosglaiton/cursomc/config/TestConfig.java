package com.santosglaiton.cursomc.config;

import com.santosglaiton.cursomc.service.DBService;
import com.santosglaiton.cursomc.service.EmailService;
import com.santosglaiton.cursomc.service.MockEmailService;
import com.santosglaiton.cursomc.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabse() throws ParseException {

        dbService.instantiateTestDatabase();

        return true;
    }

//    @Bean
//    public EmailService emailService(){
//        return new MockEmailService();
//    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }


}
