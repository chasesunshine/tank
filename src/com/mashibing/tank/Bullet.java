package com.mashibing.tank;

import java.awt.*;

public class Bullet {
	private static final int SPEED = 10;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	private boolean living = true;
	TankFrame tf = null;
	private int x, y;
	private Dir dir;
	private Group group = Group.BAD;
	Rectangle rect = new Rectangle();

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Bullet(int x, int y, Dir dir, Group group , TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;

		tf.bullets.add(this);
	}

	public void paint(Graphics g) {
		if(!living) {
			tf.bullets.remove(this);
		}

		switch(dir) {
			case LEFT:
				g.drawImage(ResourceMgr.bulletL,x,y,null);
				break;
			case UP:
				g.drawImage(ResourceMgr.bulletU,x,y,null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.bulletR,x,y,null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.bulletD,x,y,null);
				break;
		}

		move();
	}

	private void move() {
		switch (dir) {
			case LEFT:
				x -= SPEED;
				break;
			case UP:
				y -= SPEED;
				break;
			case RIGHT:
				x += SPEED;
				break;
			case DOWN:
				y += SPEED;
				break;
		}

		//update rect
		rect.x = this.x;
		rect.y = this.y;

		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			living = false;
		}
	}

	public void collideWith(Tank tank) {
		//这个地方判断碰撞，如果子弹和坦克是一组的，直接返回
		if(this.group == tank.getGroup()){
			return;
		}

		/*
			// 获取子弹
			Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
			// 获取坦克
			Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		*/

		// 子弹遇到坦克，子弹和坦克都消失
		if(rect.intersects(tank.rect)){
			tank.die();
			this.die();

			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;

			tf.explodes.add(new Explode(eX,eY,tf));
		}
	}

	private void die() {
		this.living =false;
	}
}
