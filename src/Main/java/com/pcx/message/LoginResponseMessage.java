package com.pcx.message;

public class LoginResponseMessage extends AbstractResponseMessage{
    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }

    public LoginResponseMessage() {

    }

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }
}
