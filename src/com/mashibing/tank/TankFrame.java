package com.mashibing.tank;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
	Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);
	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	List<Explode> explodes = new ArrayList<>();

	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);

		// 键盘监听器
		this.addKeyListener(new MyKeyListener());

		//窗口监听器
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量："+ bullets.size(),10,60);
		g.drawString("敌人的数量："+ tanks.size(),10,60);
		g.drawString("爆炸的数量："+ explodes.size(),10,60);
		g.setColor(color);

		// 画出我方坦克
		myTank.paint(g);
//		myTank.setMoving(false);

		// 画出子弹数量
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}

		// 画出敌方坦克数量
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}

		// 画出爆炸
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}

		// 画出子弹与坦克碰撞
		// 每一颗子弹与每一辆坦克碰撞
		for(int i=0; i<bullets.size(); i++) {
			for(Tank t : tanks ) {
				bullets.get(i).collideWith(t);
			}
		}

	}

	class MyKeyListener extends KeyAdapter {
		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = true;
					break;
				case KeyEvent.VK_UP:
					bU = true;
					break;
				case KeyEvent.VK_RIGHT:
					bR = true;
					break;
				case KeyEvent.VK_DOWN:
					bD = true;
					break;
				default:
					break;
			}

			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;

				case KeyEvent.VK_CONTROL:
					myTank.fire();
					break;

				default:
					break;
			}

			setMainTankDir();
		}

		private void setMainTankDir() {
			myTank.setMoving(true);

			if (bL) {
				myTank.setDir(Dir.LEFT);
			}
			if (bU) {
				myTank.setDir(Dir.UP);
			}
			if (bR) {
				myTank.setDir(Dir.RIGHT);
			}
			if (bD) {
				myTank.setDir(Dir.DOWN);
			}

			if (!bL && !bU && !bR && !bD) {
				myTank.setMoving(false);
			}

		}
	}

	// 不用管，双缓冲
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

}




