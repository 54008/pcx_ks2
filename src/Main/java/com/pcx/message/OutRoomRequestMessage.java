package com.pcx.message;

import lombok.Data;

@Data
public class OutRoomRequestMessage extends Message{
    private String username;
    @Override
    public int getMessageType() {
        return OutRoomRequestMessage;
    }
}
