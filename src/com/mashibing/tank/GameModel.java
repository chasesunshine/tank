package com.mashibing.tank;

import com.mashibing.tank.cor.BulletTankCollider;
import com.mashibing.tank.cor.ColliderChain;
import com.mashibing.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 既是门面，也是调停者
 */
public class GameModel {

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);

    //    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    ColliderChain colliderChain = new ColliderChain();

    private List<GameObject> objects = new ArrayList<>();

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public GameModel(){
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").toString());

        //初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            add(new Tank(50 + i*80, 200, Dir.DOWN , Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量："+ bullets.size(),10,60);
//        g.drawString("敌人的数量："+ tanks.size(),10,60);
//        g.drawString("爆炸的数量："+ explodes.size(),10,60);
        g.setColor(color);

        // 画出我方坦克
        myTank.paint(g);
//		myTank.setMoving(false);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //互相碰撞（修改的）
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1 ; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                colliderChain.collide(o1,o2);
            }
        }

        // 画出子弹与坦克碰撞
        // 每一颗子弹与每一辆坦克碰撞
//        for(int i=0; i<bullets.size(); i++) {
//            for(Tank t : tanks ) {
//                bullets.get(i).collideWith(t);
//            }
//        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
