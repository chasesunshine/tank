package com.mashibing.tank.cor;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

public class BulletTankCollider implements Collider{

    @Override
    public void collids(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){

        }
    }

}
