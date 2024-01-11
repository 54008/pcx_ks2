package com.pcx.message;


import lombok.Data;

@Data
public class RoomChessMoveRequestMessage extends Message {
    private String group;
    private int playid;
    private int chessidsrc;
    private int chessidto;
    private int x;
    private int y;
    private  boolean Iseat;
    private boolean ispk;
    private int typetwo;

    @Override
    public int getMessageType() {
        return RoomChessMoveRequestMessage;
    }
}
