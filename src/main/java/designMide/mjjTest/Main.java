package designMide.mjjTest;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer(new XijieHardDisk(), new IntelCpu(), new KingstonMermory());
        computer.run();
    }
}
