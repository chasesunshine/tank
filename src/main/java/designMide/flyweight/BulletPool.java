package designMide.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 享元模式
 */
class Bullet {
    public  UUID id = UUID.randomUUID();
    boolean living = true;

    @Override
    public String toString() {
        return "Bullet{" +
                "id=" + id +
                ", living=" + living +
                '}';
    }
}
public class BulletPool{
    List<Bullet> bullets = new ArrayList<>();
    {
        for (int i = 0; i < 5; i++) {
            bullets.add(new Bullet());
        }
    }

    /**
     * 如果 list 里面 有活着的 bullet 就拿来使用，如果没有就new一个
     * @return
     */
    public Bullet getBullet(){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if(!bullet.living){
                return bullet;
            }
        }
        return new Bullet();
    }

    public static void main(String[] args) {
        BulletPool bulletPool = new BulletPool();
        for (int i = 0; i < 10; i++) {
            Bullet bullet = bulletPool.getBullet();
            System.out.println(bullet);
        }
    }
}

