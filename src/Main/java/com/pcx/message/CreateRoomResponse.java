package com.pcx.message;

public class CreateRoomResponse extends AbstractResponseMessage{
    @Override
    public int getMessageType() {
        return CreateRoomResponse;
    }

    public CreateRoomResponse(boolean success, String reason) {
        super(success, reason);
    }
}
