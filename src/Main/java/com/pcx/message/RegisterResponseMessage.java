package com.pcx.message;

public class RegisterResponseMessage extends AbstractResponseMessage{
    @Override
    public int getMessageType() {
        return RegisterResponseMessage;
    }
    public RegisterResponseMessage(){

    }

    public RegisterResponseMessage(boolean success, String reason) {
        super(success, reason);
    }
}
