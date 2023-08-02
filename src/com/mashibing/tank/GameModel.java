package com.mashibing.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").toString());

        //初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            this.tanks.add(new Tank(50 + i*80, 200, Dir.DOWN , Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+ bullets.size(),10,60);
        g.drawString("敌人的数量："+ tanks.size(),10,60);
        g.drawString("爆炸的数量："+ explodes.size(),10,60);
        g.setColor(color);

        // 画出我方坦克
        myTank.paint(g);
//		myTank.setMoving(false);

        // 画出子弹数量
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // 画出敌方坦克数量
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        // 画出爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        // 画出子弹与坦克碰撞
        // 每一颗子弹与每一辆坦克碰撞
        for(int i=0; i<bullets.size(); i++) {
            for(Tank t : tanks ) {
                bullets.get(i).collideWith(t);
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
