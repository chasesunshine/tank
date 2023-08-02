package com.mashibing.tank;

import com.mashibing.tank.strategy.DefaultFireStrategy;
import com.mashibing.tank.strategy.FireStrategy;
import com.mashibing.tank.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    public int x, y;
    public Dir dir = Dir.DOWN;
    private final static int SPEED = 2;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    private boolean moving = true;
    TankFrame tf = null;
    private boolean living = true;
    private Random random = new Random();
    public Group group = Group.BAD;
    Rectangle rect = new Rectangle();
    FireStrategy fs = new FourDirFireStrategy();
    public GameModel gm;

    public Tank(int x, int y, Dir dir,Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }



    @Override
    public void paint(Graphics g) {
        if(!living){
            gm.remove(this);
        }

        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

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
        if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) {
            y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    // 开火
    // 1. fire(FireStrategy f)每次调用都要new，应该把 DefaultFireStrategy 设置成单例
    // 2. 把 FireStrategy 设计成 成员变量
    public void fire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
    }

}
