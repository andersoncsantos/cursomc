package com.anderson.cursomc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

@Slf4j
public class MockEmailService extends AbstractEmailService {

    /*Without Lombok
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);*/

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Simulando envio de email ...");
        log.info(msg.toString());
        log.info("Email enviado");

    }
}
