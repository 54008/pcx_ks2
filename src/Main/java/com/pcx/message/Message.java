package com.pcx.message;

import lombok.Data;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class Message implements Serializable {
    public static Class<? extends  Message> getMessageClass(int messageType){
       return messageClasses.get(messageType);
    }
    private  int sequenceId;
    private  int messageType;


    public static  final  int LoginRequestMessage = 0;
    public static  final  int LoginResponseMessage = 1;
    public static  final  int RegisterRequestMessage = 2;
    public static  final  int RegisterResponseMessage = 3;
    public static  final  int FindRoomRequestMessage = 4;
    public static  final  int FindRoomResponseMessage = 5;
    public static  final  int GoRoomRequestMessage = 6;
    public static  final  int GoRoomResponseMessage = 7;

    public static  final  int CreateRoomMessage = 8;
    public static  final  int CreateRoomResponse = 9;
    public static  final  int RoomChessMoveRequestMessage = 10;
    public static  final  int OutRoomRequestMessage = 11;
    public static  final  int PKMessage = 12;
    public static  final  int StatsMessage = 13;

    public abstract int getMessageType();
    private static final Map<Integer, Class<? extends Message>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(LoginRequestMessage, LoginRequestMessage.class);
        messageClasses.put(LoginResponseMessage, LoginResponseMessage.class);
        messageClasses.put(RegisterRequestMessage,RegisterRequestMessage.class);
        messageClasses.put(RegisterResponseMessage,RegisterResponseMessage.class);
        messageClasses.put(FindRoomRequestMessage,FindRoomRequestMessage.class);
        messageClasses.put(FindRoomResponseMessage,FindRoomResponseMessage.class);
        messageClasses.put(GoRoomRequestMessage,GoRoomRequestMessage.class);
        messageClasses.put(GoRoomResponseMessage,GoRoomResponseMessage.class);
        messageClasses.put(CreateRoomMessage,CreateRoomMessage.class);
        messageClasses.put(CreateRoomResponse,CreateRoomResponse.class);
        messageClasses.put(RoomChessMoveRequestMessage, RoomChessMoveRequestMessage.class);
        messageClasses.put(OutRoomRequestMessage, OutRoomRequestMessage.class);
        messageClasses.put(PKMessage, PKMessage.class);
        messageClasses.put(StatsMessage, StatsMessage.class);

    }
}
