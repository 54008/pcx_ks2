package com.pcx.message;

import lombok.Data;

@Data
public class PKMessage extends Message{
    private String  username;
    private  boolean isWin;
    @Override
    public int getMessageType() {
        return PKMessage;
    }
}
