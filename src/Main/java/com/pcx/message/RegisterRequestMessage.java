package com.pcx.message;

import lombok.Data;

@Data
public class RegisterRequestMessage extends Message{
    private String userid;
    private String password;
    private String nickname;
    private String sex;
    private String data;
    private int province_id;
    @Override
    public int getMessageType() {
        return RegisterRequestMessage;
    }

    public RegisterRequestMessage(String userid, String password, String nickname, String sex, String data, int province_id) {
        this.userid = userid;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.data = data;
        this.province_id = province_id;
    }
}
