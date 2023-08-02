package com.mashibing.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            // 每0.05ms重新刷新一次
            tankFrame.repaint();
        }
    }
}
