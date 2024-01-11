package com.pcx.client.handle;

import com.pcx.Game.GamePanel;

import com.pcx.client.Client;
import com.pcx.gui.GameMain;
import com.pcx.message.PKMessage;
import com.pcx.message.RoomChessMoveRequestMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.swing.*;
import java.awt.*;

public class RecordHandle extends SimpleChannelInboundHandler<RoomChessMoveRequestMessage> {
    static GameMain gameMain;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RoomChessMoveRequestMessage roomChessMoveRequestMessage) throws Exception {

        if (roomChessMoveRequestMessage.getPlayid()!= Client.plagid){
            System.out.println(roomChessMoveRequestMessage);
            switch (roomChessMoveRequestMessage.getTypetwo()) {
            case 1:
                GamePanel.getChesses()[roomChessMoveRequestMessage.getChessidsrc()].setP(
                        new Point(roomChessMoveRequestMessage.getX(),
                                roomChessMoveRequestMessage.getY()));

                //解锁棋盘
                GamePanel.setIslocke(false);
                //修改提示文字
                gameMain.getGamePanel().repaint();
                break;
            case 2:
                System.out.println("ated");
                System.out.println(roomChessMoveRequestMessage);
                if (GamePanel.getChesses()[roomChessMoveRequestMessage.getChessidto()].getName().equals("boss")) {
                    RoomChessMoveRequestMessage roomChessMoveRequestMessage1 = new RoomChessMoveRequestMessage();
                    roomChessMoveRequestMessage1.setTypetwo(51);
                    roomChessMoveRequestMessage1.setGroup(Client.group);
                    roomChessMoveRequestMessage1.setPlayid(Client.plagid);
                    System.out.println("loss");
                    Client.group = null;
                    channelHandlerContext.writeAndFlush(roomChessMoveRequestMessage1);
                    PKMessage pkMessage = new PKMessage();
                    pkMessage.setWin(false);
                    pkMessage.setUsername(Userinfo.register.getUserid());
                    Client.channel.writeAndFlush(pkMessage);
                    gameMain.getGamePanel().setVisible(false);
                    GameMain.getGame().dispose();
                    JOptionPane.showMessageDialog(null, "你输了", "系统提示", JOptionPane.PLAIN_MESSAGE);


                }
                GamePanel.getChesses()[roomChessMoveRequestMessage.getChessidto()] = null;
                GamePanel.getChesses()[roomChessMoveRequestMessage.getChessidsrc()].setP(
                        new Point(roomChessMoveRequestMessage.getX(),
                                roomChessMoveRequestMessage.getY()));
                //删除吃子

                GamePanel.setIslocke(false);
                gameMain.getGamePanel().repaint();
                break;

            case 3: {
                gameMain = new GameMain();
            }
            break;
            case 51: {

                if (roomChessMoveRequestMessage.getPlayid() != Client.plagid) ;
                PKMessage pkMessage = new PKMessage();
                pkMessage.setWin(true);
                pkMessage.setUsername(Userinfo.register.getUserid());
                Client.channel.writeAndFlush(pkMessage);
                JOptionPane.showMessageDialog(null, "你赢了", "系统提示", JOptionPane.PLAIN_MESSAGE);


                gameMain.getGamePanel().setVisible(false);
                GameMain.getGame().dispose();


                Client.group = null;
            }
            default:
                break;

        }
        }
    }
}


