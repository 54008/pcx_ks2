package com.pcx.message;

import lombok.Data;

import java.util.Map;
@Data
public class FindRoomResponseMessage extends AbstractResponseMessage{
    Map<String,Integer> res;

    public FindRoomResponseMessage(Map<String, Integer> res) {
        this.res = res;
    }

    public FindRoomResponseMessage(boolean success, String reason, Map<String, Integer> res) {
        super(success, reason);
        this.res = res;
    }

    @Override
    public int getMessageType() {
        return FindRoomResponseMessage;
    }
}
