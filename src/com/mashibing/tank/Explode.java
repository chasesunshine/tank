package com.mashibing.tank;

import java.awt.*;

public class Explode {
	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	private int x, y;
	private int step = 0;
	TankFrame tf = null;
	
	public Explode(int x, int y,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.tf = tf;

		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);

		// 爆炸结束之后从list中去除
		if(step >= ResourceMgr.explodes.length) {
			tf.explodes.remove(this);
		}
	}
}
