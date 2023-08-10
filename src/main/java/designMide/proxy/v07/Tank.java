package designMide.proxy.v07;

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
//        TankTimeProxy tankTimeProxy = new TankTimeProxy(new TankLogProxy(new Tank()));
//        tankTimeProxy.move();

        TankLogProxy tankLogProxy = new TankLogProxy(new TankTimeProxy(new Tank()));
        tankLogProxy.move();
    }
}

class TankTimeProxy implements Movable{
    Movable m;

    public TankTimeProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class TankLogProxy implements Movable{
    Movable m;

    public TankLogProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("start moving");
        m.move();
        System.out.println("stop moving");
    }
}

interface Movable{
    void move();
}