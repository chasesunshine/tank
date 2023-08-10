package designMide.proxy.v08;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
        // reflection 通过二进制字节码分析类的属性和方法
        Movable m = (Movable)Proxy.newProxyInstance(Tank.class.getClassLoader(), new Class[]{Movable.class}, new LogHandler(new Tank()));
        m.move();
    }
}

class LogHandler implements InvocationHandler{
    Tank tank;

    public LogHandler(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("mehhod " + method.getName() + " start..");
        Object o = method.invoke(tank, args);
        System.out.println("mehhod " + method.getName() + " end..");
        return o;
    }
}

interface Movable{
    void move();
}