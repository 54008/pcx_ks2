package com.pcx.gui;

import com.pcx.Game.GamePanel;

import javax.swing.*;


public class GameMain{
    public static JFrame getGame() {
        return game;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
    static JFrame game;
    static GamePanel gamePanel;
    public GameMain() {

        if (gamePanel == null){
            gamePanel = new GamePanel();

        }
        if (game == null){
            game = new JFrame();
            game.setSize(400,500);
            game.setLocationRelativeTo(null);
            //设置居中
            game.setDefaultCloseOperation(2);

            game.add(gamePanel);
        }
        gamePanel.createChesses();
        game.setVisible(true);
        gamePanel.setVisible(true);
    }
}
