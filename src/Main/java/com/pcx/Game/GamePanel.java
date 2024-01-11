package com.pcx.Game;



import com.pcx.client.Client;
import com.pcx.message.RoomChessMoveRequestMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class GamePanel extends JPanel {

    static Chess selectedChess;
    static Chess[] chesses = new Chess[32];
    static   int curPlayer = 0;
    static   boolean islocke = false ;

    public static void setSelectedChess(Chess selectedChess) {
        GamePanel.selectedChess = selectedChess;
    }

    public static void setChesses(Chess[] chesses) {
        GamePanel.chesses = chesses;
    }

    public static void setCurPlayer(int curPlayer) {
        GamePanel.curPlayer = curPlayer;
    }

    public static void setIslocke(boolean islocke) {
        GamePanel.islocke = islocke;
    }

    public static Chess getSelectedChess() {
        return selectedChess;
    }

    public static Chess[] getChesses() {
        return chesses;
    }

    public static int getCurPlayer() {
        return curPlayer;
    }

    public static boolean isIslocke() {
        return islocke;
    }

    public GamePanel() {
        createChesses();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(islocke);
                if (islocke){
                    return;
                }
               Point p = Chess.getPointFromXY(e.getX(),e.getY());
                if (selectedChess == null){
                    selectedChess = getChessByp(p);
                }else {
                    Chess c = getChessByp(p);
                    if (c !=null){
                        if (c.getPlayer() == selectedChess.getPlayer()){
                            selectedChess = getChessByp(p);
                            System.out.println("重新选择");
                        }else {
                            if (selectedChess.isAbleMove(p,GamePanel.this)){
                            //
                                RoomChessMoveRequestMessage roomChessMoveRequestMessage =
                                        new RoomChessMoveRequestMessage();
                                roomChessMoveRequestMessage.setChessidsrc(selectedChess.getIndex());
                                roomChessMoveRequestMessage.setChessidto(c.getIndex());
                                roomChessMoveRequestMessage.setIseat(true);
                                roomChessMoveRequestMessage.setX(p.x);
                                roomChessMoveRequestMessage.setY(p.y);
                                roomChessMoveRequestMessage.setTypetwo(2);
                                roomChessMoveRequestMessage.setGroup(Client.group);
                                roomChessMoveRequestMessage.setPlayid(Client.plagid);

                                Client.channel.writeAndFlush(roomChessMoveRequestMessage);
                                chesses[c.getIndex()] = null;
                                selectedChess.setP(p);
                                GamePanel.setIslocke(true);
                                System.out.println("eat");

                            }

                        }
                    }else {
                        if(selectedChess.isAbleMove(p,GamePanel.this)){
                            selectedChess.isAbleMove(p,GamePanel.this);
                            selectedChess.setP(p);
                            RoomChessMoveRequestMessage roomChessMoveRequestMessage =
                                    new RoomChessMoveRequestMessage();
                            roomChessMoveRequestMessage.setPlayid(Client.plagid);
                            roomChessMoveRequestMessage.setChessidsrc(selectedChess.getIndex());
                            roomChessMoveRequestMessage.setIseat(false);
                            roomChessMoveRequestMessage.setX(p.x);
                            roomChessMoveRequestMessage.setY(p.y);
                            roomChessMoveRequestMessage.setTypetwo(1);
                            roomChessMoveRequestMessage.setGroup(Client.group);

                            Client.channel.writeAndFlush(roomChessMoveRequestMessage);
                            GamePanel.setIslocke(true);
                        }
                        System.out.println("移动");

                    }
                }
                repaint();
            }

        });
    }

    public   Chess getChessByp(Point p){
        for (Chess chess : chesses) {
            if (chess !=null && chess.getP().equals(p)){
                return chess;
            }
        }
        return null;
    }
      public void createChesses(){
        String[] names = {"che", "ma", "xiang", "shi", "boss", "shi",
                "xiang", "ma", "che", "pao", "pao", "bing", "bing", "bing"
                , "bing", "bing"};
        int[] xs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 8, 1, 3, 5, 7, 9};


        for (int i = 0 ;i < names.length;i++){
            Chess c = ChessFactory.create(names[i], 0, xs[i]);
            chesses[i] = c;
            c.setIndex(i);
        }

        for (int i = 0;i< names.length;i++){
            Chess c = ChessFactory.create(names[i], 1, xs[i]);
            c.reserve();
            c.setIndex(i + 16);
            chesses[c.getIndex()] = c;//将棋子保存到数组
        }

    }
    private  void drawChesses(Graphics g){
        for (Chess chess : chesses) {
            if (chess == null) continue;
            chess.draw(g,this);
        }

    }
    public  void paint(Graphics g){
        super.paint(g);
        String bgPath = "pic"+File.separator+"qipan.jpg";
        Image bgImg = Toolkit.getDefaultToolkit().getImage(bgPath);
        g.drawImage(bgImg,0,0,this);



        drawChesses(g);

        if (selectedChess != null){
            selectedChess.drawRect(g);
        }


    }

}
