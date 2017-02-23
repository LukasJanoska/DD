package com.damidev.dd.notregistred.login.dataaccess;


public class RegistrationRequestDto {

    private final String email;
    private final String password;

    public RegistrationRequestDto(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
