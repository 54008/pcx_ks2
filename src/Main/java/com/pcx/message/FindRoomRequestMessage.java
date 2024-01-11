package com.pcx.message;

import lombok.Data;

@Data
public class FindRoomRequestMessage extends Message{
    private String username;

    public FindRoomRequestMessage(String username) {
        this.username = username;
    }

    @Override
    public int getMessageType() {
        return FindRoomRequestMessage;
    }
}
