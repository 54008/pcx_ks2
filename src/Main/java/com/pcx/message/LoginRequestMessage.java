package com.pcx.message;

import lombok.Data;

@Data
public class LoginRequestMessage extends Message{
    private  String username;
    private  String password;
    private  String repass;
    public LoginRequestMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequestMessage(String username, String password, String repass) {
        this.username = username;
        this.password = password;
        this.repass = repass;
    }

    @Override

    public int getMessageType() {
        return LoginRequestMessage;
    }
}
