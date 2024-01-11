package com.pcx.client.handle;

import com.pcx.message.RegisterRequestMessage;
import lombok.Data;

import javax.swing.*;
@Data
public class Userinfo
{
    static   int score;
    static   int vic;
    static   int los;
    static   int win_rate;

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Userinfo.score = score;
    }

    public static int getVic() {
        return vic;
    }

    public static void setVic(int vic) {
        Userinfo.vic = vic;
    }

    public static int getLos() {
        return los;
    }

    public static void setLos(int los) {
        Userinfo.los = los;
    }

    public static int getWin_rate() {
        return win_rate;
    }

    public static void setWin_rate(int win_rate) {
        Userinfo.win_rate = win_rate;
    }

    static RegisterRequestMessage register;

    static JFrame loginjFrame;
    static JFrame mainJrame;

    public Userinfo() {
    }

    static public void loginInit(JFrame loginj) {
        loginjFrame = loginj;
    }
    static public void MaInit(JFrame loginj) {
        mainJrame = loginj;
    }
    static public void reInit(RegisterRequestMessage loginj) {
        register = loginj;
    }
}
