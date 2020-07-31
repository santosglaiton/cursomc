package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.Cliente;
import com.santosglaiton.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
