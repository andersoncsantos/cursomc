package com.anderson.cursomc.services;

import com.anderson.cursomc.domain.Cliente;
import com.anderson.cursomc.repositories.ClienteRepository;
import com.anderson.cursomc.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private final Random random = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (Objects.isNull(cliente)) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPassword = newPassword();
        cliente.setSenha(bCryptPasswordEncoder.encode(newPassword));
        clienteRepository.save(cliente);

        //emailService.sendNewPasswordEmail(cliente, newPassword);
       log.info("email: " + cliente.getEmail() + " nova_senha: " + newPassword);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3); // 0, 1, 2
        if (opt == 0) { // generate a digit
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) { // generate uppercase
            return (char) (random.nextInt(26) + 65);
        } else { // generate lowercase
            return (char) (random.nextInt(26) + 97);
        }
    }
}
