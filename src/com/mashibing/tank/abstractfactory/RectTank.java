package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    int x, y;
    Dir dir = Dir.DOWN;
    private final static int SPEED = 5;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private boolean moving = true;
    TankFrame tf = null;
    private boolean living = true;
    private Random random = new Random();
    Group group = Group.BAD;
    public Rectangle rect = new Rectangle();
    FireStrategy fs = new FourDirFireStrategy();

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD){
            String goodFSName = (String)PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            fs = new DefaultFireStrategy();
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void paint(Graphics g) {
        if(!living){
            tf.tanks.remove(this);
        }

//        switch(dir) {
//            case LEFT:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
//                break;
//            case UP:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
//                break;
//            case RIGHT:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
//                break;
//            case DOWN:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
//                break;
//        }
        Color color = g.getColor();
        g.setColor(group == Group.GOOD?Color.RED:Color.BLUE);
        g.fillRect(x,y,40,40);
        g.setColor(color);
        move();
    }

    // 移动的时候有 random 几率开火
    private void move() {
        if(!moving){
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        // 敌方坦克随机移动，己方坦克不随机移动
        if(this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        // 边界检测
        boundsCheck();
    }

    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH- RectTank.WIDTH -2) {
            x = TankFrame.GAME_WIDTH - RectTank.WIDTH -2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2 ) {
            y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    // 开火
    // 1. fire(FireStrategy f)每次调用都要new，应该把 DefaultFireStrategy 设置成单例
    // 2. 把 FireStrategy 设计成 成员变量
    public void fire() {
    //        fs.fire(this);
        int bX = this.x + RectTank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + RectTank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for (Dir dir: dirs) {
            this.tf.gf.createBullet(bX,bY,dir,this.group,this.tf);
        }
    }

    @Override
    public void die() {
        this.living = false;
    }

}
