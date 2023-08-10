package designMide.proxy.v04;

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
        Tank2 tank2 = new Tank2();
        tank2.move();
    }
}

class Tank2 extends Tank{
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        super.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

interface Movable{
    void move();
}