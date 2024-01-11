package com.pcx.message;

import lombok.Data;

@Data
public class CreateRoomMessage extends Message{
    private String username;
    @Override
    public int getMessageType() {
        return CreateRoomMessage;
    }
}
