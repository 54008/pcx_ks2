package com.pcx.Game;

import java.awt.*;

public class Boss extends Chess{
    public Boss(int player, Point p) {
        super("boss", player, p);
    }

    public Boss(int player, int px) {
        this(player, new Point(px, 1));
    }

    @Override
    public boolean isAbleMove(Point tp, GamePanel gamePanel) {
        return line(tp) > 1 && isHome(tp) && getStep(tp) == 1;
    }
}
