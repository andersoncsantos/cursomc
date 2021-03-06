package com.anderson.cursomc.services;

import com.anderson.cursomc.domain.Cliente;
import com.anderson.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido pedido);
    void sendEmail(SimpleMailMessage msg);
    void sendOrderConfirmationHtmlEmail(Pedido pedido);
    void sendHtmlEmail(MimeMessage mimeMessage);
    void sendNewPasswordEmail(Cliente cliente, String newPassword);
}
