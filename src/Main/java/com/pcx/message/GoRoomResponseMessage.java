package com.pcx.message;

public class GoRoomResponseMessage extends AbstractResponseMessage{
    @Override
    public int getMessageType() {
        return GoRoomResponseMessage;
    }

    public GoRoomResponseMessage(boolean success, String reason) {
        super(success, reason);
    }
}
