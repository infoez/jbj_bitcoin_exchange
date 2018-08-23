package com.account.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

public class RegistrationVO {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    private boolean ok;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
