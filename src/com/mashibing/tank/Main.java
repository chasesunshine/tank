package com.mashibing.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount").toString());

        //初始化敌方坦克
        for(int i=0; i<initTankCount; i++) {
            tankFrame.tanks.add(tankFrame.gf.createTank(50 + i*80, 200, Dir.DOWN , Group.BAD,tankFrame));
        }

        while (true){
            Thread.sleep(50);
            // 每0.05ms重新刷新一次
            tankFrame.repaint();
        }
    }
}
