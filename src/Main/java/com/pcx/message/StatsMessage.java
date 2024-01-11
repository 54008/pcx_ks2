package com.pcx.message;

import lombok.Data;

@Data
public class StatsMessage extends Message{
    private  int score;
    private  int vic;
    private  int los;
    private  int win_rate;
    @Override
    public int getMessageType() {
        return StatsMessage;
    }
}
