package com.pcx.Game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

public abstract class Chess implements Serializable {
    private final static   int SIZE = 30;
    private final static int  MARGIN = 20;
    private final static int SPACE = 40;
    private  String name;
    private  final  String suffix = ".png";
    protected int player ;
    private  int x,y;
    private int index;

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    protected Point p;
    //棋子的网格坐标，初始位置，不可改变
    private Point initP;
    public Chess(String name, int player, Point p) {
        this.name = name;
        this.player = player;
        setP(p);
    }

    public Chess(String name, Point p, int player) {
        this.name = name;
        this.player = player;
        setP(p);
    }

    public void draw(Graphics g, JPanel panel){
        String path = "pic" + File.separator + name + player +suffix;
        Image image = Toolkit.getDefaultToolkit().getImage(path);

        g.drawImage(image,x,y,SIZE,SIZE,panel);
    }

    public void CalXY(){
        x = MARGIN - SIZE / 2 +SPACE * (p.x - 1);
        y = MARGIN - SIZE / 2 +SPACE * (p.y - 1);
    }

    public  static  Point getPointFromXY(int x,int y){
        Point p = new Point();
        p.x = (x - MARGIN + SIZE / 2) / SPACE +1;
        p.y = (y - MARGIN + SIZE / 2) / SPACE +1;
        if (p.x < 1 || p.x >10 || p.y < 1 || p.y >11){
            return  null;
        }
        return  p;
    }
    public int getSIZE() {
        return SIZE;
    }

    public int getMARGIN() {
        return MARGIN;
    }

    public int getSPACE() {
        return SPACE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = (Point) p.clone();
        if (initP == null){
            initP = this.p;
        }
        CalXY();
    }
    public  void reserve(){
        p.x = 10 -p.x;
        p.y = 11 - p.y;
        initP = p;
        CalXY();
    }
    public  void drawRect(Graphics g){
        g.drawRect(x,y,SIZE,SIZE);
    }
    public  boolean isBejJiao(Point tp, GamePanel gamePanel){
        Point center = new Point();
        if ("xiang".equals(name)){

            center.x = (p.x + tp.x) /2;
            center.y = (p.y + tp.y) / 2;
            return  gamePanel.getChessByp(center) == null;

        }else if ("ma".equals(name)){
            int line = line(tp);
            if (line== 0){
                center.x = (p.x + tp.x)/2;
                center.y = p.y;
            }else  if(line == -1){
                center.y = (p.y +tp.y)/2;
                center.x = p.x;
            }
            return  gamePanel.getChessByp(center) == null;
        }
        return  false;
    }


    public boolean isAbleMove(Point tp, GamePanel gamePanel){
        if ("boss".equals(name)){
            return line(tp) > 1 && isHome(tp) &&getStep(tp) == 1;
        }else if("shi".equals(name)){
            return line(tp) == 1 && isHome(tp) &&getStep(tp) == 1;
        }else if ("xiang".equals(name)){
            return line(tp) == 1 &&getStep(tp)==2
                    &&isBejJiao(tp,gamePanel)&&
                    !isOverRiver(tp);
        }else  if ("ma".equals(name)){

            return (line(tp) == 0 ||line(tp) == -1)&&isBejJiao(tp,gamePanel);
        }else  if ("che".equals(name)){
            return line(tp) >1 && getCount(tp,gamePanel) == 0;
        }else  if ("pao".equals(name)){
            Chess c = gamePanel.getChessByp(tp);
            if (c!=null){
                if (c.getPlayer() !=this.player){
                    return line(tp) > 1 &&getCount(tp,gamePanel) == 1;
                }
            }else {
                return  line(tp) >1 && getCount(tp,gamePanel) == 0;
            }
        }else if ("bing".equals(name) ){
            if (line(tp) < 2 || getStep(tp)>1){
                return false;
            }
            if(isOverRiver(p)){
                return !isBack(tp);
            }else {
                return  isForward(tp);
            }
        }
        return true;
    }
    public  boolean isForward(Point tp){
        int isUpOrDown  = isUpOrDown();
        if (isUpOrDown == 1){
            if (tp.y >p.y){
                return true;
            }
        }else if (isUpOrDown == 2){
            if (tp.y <p.y){
                return true;
            }
        }
        return false;
    }

    public  boolean isBack(Point tp){
        int upOrDown = isUpOrDown();
        if (upOrDown == 1){
            if (tp.y <p.y){
                return true;
            }
        }else if(upOrDown == 2){
            if (tp.y >p.y){
                return true;
            }
        }
        return  false;
    }
    public  boolean isOverRiver(Point tp){
        int UpOrDown = isUpOrDown();
        if (UpOrDown == 1){
            if (tp.y >5){
                return  true;
            }
        }else if (UpOrDown == 2){
            if (tp.y < 6){
                return  true;
            }
        }
        return false;
    }
    public  int line (Point tp){
        if (p.y == tp.y){
            return 3;
        }else  if (p.x == tp.x){
            return 2;
        }else if(Math.abs(p.x - tp.x) == Math.abs(p.y - tp.y)){
            return 1;
        }else {
            if (Math.abs(p.x - tp.x) == 2 && Math.abs(p.y - tp.y) == 1)
            {
                return 0;
            }else if  (Math.abs(p.x - tp.x) == 1 && Math.abs(p.y - tp.y) == 2)
            {
                return -1;
            }
        }
        return  -2;

    }
    public int isUpOrDown(){
        if (initP.y <= 5) {
            return 1;
        }else if (initP.y >5){
            return 2;
        }
        return 0;
    }
    public boolean isHome(Point tp){
        if (tp.x < 4 ||tp.x > 6){
            return false;
        }
        int upDown =isUpOrDown();
        if (upDown == 1){
            if (tp.y >3 ||tp.y<1){
                return false;
            }
        }else if (upDown == 2){
            if (tp.y >10 ||tp.y <8){
                return false;
            }

        }
        return true;
    }
    public int getStep(Point tp){
        int line = line(tp);
        if (line == 3){
            return Math.abs(p.x - tp.x);

        }else if(line == 2 ||line == 1) {
            return Math.abs(p.y - tp.y);
        }
        return 0;
    }
    public  int getCount(Point tp, GamePanel gamePanel){
        int start = 0;
        int end = 0;
        int count = 0;//统计棋子数量
        int line = line(tp);
        Point np = new Point();
        if (line == 2) {
            //y
            np.x = tp.x;
            if (tp.y > p.y) {
                //up to d
                start = p.y + 1;
                end = tp.y;
            } else {
                //down to up
                start = tp.y + 1;
                end = p.y;
            }
            for (int i = start; i < end; i++) {
                np.y = i;
                if (gamePanel.getChessByp(np) != null) {
                    count++;
                }
            }
        }else if (line == 3) {
            //x
            np.y = tp.y;
            if (tp.x > p.x) {
                //left to rigt
                start = p.x + 1;
                end = tp.x;
            } else {
                //right to left
                start = tp.x + 1;
                end = p.x;
            }
            System.out.println("start:" + start);
            System.out.println("end:" + end);
            for (int i = start; i < end; i++) {
                np.x = i;
                if (gamePanel.getChessByp(np) != null) {
                    count++;
                }
            }
        }
        System.out.println("count :" + count);
        return count;
    }
}
