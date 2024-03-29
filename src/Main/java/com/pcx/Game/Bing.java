package com.pcx.Game;

import java.awt.*;

public class Bing extends Chess {
    public Bing(int player, Point p) {
        super("bing", player, p);
    }

    public Bing(int player, int px) {
        this(player, new Point(px, 4));
    }

    @Override
    public boolean isAbleMove(Point tp, GamePanel gamePanel) {
        if (line(tp) < 2 || getStep(tp) > 1) {
            return false;
        }
        if (isOverRiver(this.p)) {
            System.out.println("过了河" + !isForward(tp));
            return !isBack(tp);
        } else {
            System.out.println("没过河" + isForward(tp));
            return isForward(tp);
        }
    }
}
