package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    void sendEmail(SimpleMailMessage msg);
}
