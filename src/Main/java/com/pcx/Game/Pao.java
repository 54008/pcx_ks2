package com.pcx.Game;

import java.awt.*;

public class Pao extends Chess{
    public Pao(int player, Point p) {
        super("pao", player, p);
    }

    public Pao(int player, int px) {
        this(player, new Point(px, 3));
    }

    @Override
    public boolean isAbleMove(Point tp, GamePanel gamePanel) {
        Chess c = gamePanel.getChessByp(tp);
        if (c != null) {
            if (c.getPlayer() != this.player) {
                //³Ô×Ó
                return line(tp) > 1 && getCount(tp, gamePanel) == 1;
            }
        } else {
            //ÒÆ¶¯
            return line(tp) > 1 && getCount(tp, gamePanel) == 0;
        }

        return false;
    }
}
