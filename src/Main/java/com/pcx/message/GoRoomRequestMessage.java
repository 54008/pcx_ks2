package com.pcx.message;

import lombok.Data;

@Data
public class GoRoomRequestMessage extends Message{
    private String srcname;
    private String toname;

    public GoRoomRequestMessage(String srcname, String toname) {
        this.srcname = srcname;
        this.toname = toname;
    }

    @Override
    public int getMessageType() {
        return GoRoomRequestMessage;
    }
}

