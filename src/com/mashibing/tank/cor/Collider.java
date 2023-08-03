package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;

/**
 * 碰撞器
 */
public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}
