package designMide.proxy.v06;

import java.util.Random;

/**
 * 我想记录坦克的移动时间
 * 用继承（慎用继承 - 耦合度太高）
 */
public class Tank implements Movable{

    /**
     * 模拟坦克移动了一段时间
     */
    @Override
    public void move() {
        System.out.println("Tank moving xxx...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TankTimeProxy tankTimeProxy = new TankTimeProxy(new Tank());
        tankTimeProxy.move();
    }
}

class TankTimeProxy implements Movable{
    Tank tank;

    public TankTimeProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class TankLogProxy implements Movable{
    Tank tank;

    public TankLogProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        System.out.println("start moving");
        tank.move();
        System.out.println("stop moving");
    }
}

interface Movable{
    void move();
}