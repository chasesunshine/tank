package designMide.mjjTest;

public class Computer {
    private HardDisk hardDisk;
    private Cpu cpu;
    private Memory memory;

    public Computer(HardDisk hardDisk, Cpu cpu, Memory memory) {
        this.hardDisk = hardDisk;
        this.cpu = cpu;
        this.memory = memory;
    }

    public void run(){
        this.hardDisk.get();
        this.hardDisk.save();
        this.cpu.run();
        this.memory.run();
    }
}
