package com.pcx.Game;

import java.awt.*;

public class Ma extends Chess{
    public Ma(int player, Point p) {
        super("ma", player, p);
    }

    public Ma(int player, int px) {
        this(player, new Point(px, 1));
    }

    @Override
    public boolean isAbleMove(Point tp, GamePanel gamePanel) {
        return (line(tp) == 0 || line(tp) == -1) && isBejJiao(tp, gamePanel);
    }
}
