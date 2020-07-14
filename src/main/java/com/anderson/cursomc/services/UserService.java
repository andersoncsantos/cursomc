package com.anderson.cursomc.services;

import com.anderson.cursomc.security.SystemUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static SystemUser authenticated() {
        try {
            return (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
