package com.mashibing.tank.strategy;

import com.mashibing.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank t);
}
