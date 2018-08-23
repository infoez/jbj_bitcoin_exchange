package com.account.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class AccountVO {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    private String username;
    private boolean signed;
    private String phone;
    private String address;
    private String krw;
    private String btc;
    private String krwWaiting;
    private String btcWaiting;
    @NotNull
    @NotEmpty
    private String token;
    private long expiration;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKrw() {
        return krw;
    }

    public void setKrw(String krw) {
        this.krw = krw;
    }

    public String getBtc() {
        return btc;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }

    public String getKrwWaiting() {
        return krwWaiting;
    }

    public void setKrwWaiting(String krwWaiting) {
        this.krwWaiting = krwWaiting;
    }

    public String getBtcWaiting() {
        return btcWaiting;
    }

    public void setBtcWaiting(String btcWaiting) {
        this.btcWaiting = btcWaiting;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
