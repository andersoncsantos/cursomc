package com.anderson.cursomc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

@Slf4j
public class MockEmailService extends AbstractEmailService {

    /*Without Lombok
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);*/

    @Override
    public void sendEmail(SimpleMailMessage simpleMailMessage) {
        log.info("Simulando envio de email ...");
        log.info(simpleMailMessage.toString());
        log.info("Email enviado");

    }

    @Override
    public void sendHtmlEmail(MimeMessage mimeMessage) {
        log.info("Simulando envio de email HTML ...");
        log.info(mimeMessage.toString());
        log.info("Email enviado");
    }
}
