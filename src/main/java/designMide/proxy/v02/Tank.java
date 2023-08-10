package designMide.proxy.v02;

import java.util.Random;

/**
 * 我想记录坦克的移动时间
 */
public class Tank implements Movable{

    /**
     * 模拟坦克移动了一段时间
     */
    @Override
    public void move() {
        long start = System.currentTimeMillis();

        System.out.println("Tank moving xxx...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        Tank tank = new Tank();
        tank.move();
    }
}

interface Movable{
    void move();
}