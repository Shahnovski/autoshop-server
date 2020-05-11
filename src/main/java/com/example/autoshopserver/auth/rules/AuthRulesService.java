package com.example.autoshopserver.auth.rules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthRulesService {

    @Value("${auth.AuthRules.minPasswordLength:5}")
    private Integer minPasswordLength;

    @Value("${auth.AuthRules.maxPasswordLength:256}")
    private Integer maxPasswordLength;


    @Value("${auth.AuthRules.minLoginLength:5}")
    private Integer minLoginLength;

    @Value("${auth.AuthRules.maxLoginLength:256}")
    private Integer maxLoginLength;


    public boolean checkPassword(String password) {
        if (password.length() < minPasswordLength) return false;
        return password.length() <= maxPasswordLength;
    }

    public boolean checkLogin(String login) {
        if (login.length() < minLoginLength) return false;
        return login.length() <= maxLoginLength;
    }
}
