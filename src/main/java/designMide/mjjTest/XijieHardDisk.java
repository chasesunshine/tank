package designMide.mjjTest;

public class XijieHardDisk implements HardDisk{
    @Override
    public void save() {
        System.out.println("XijieHardDisk save");
    }

    @Override
    public void get() {
        System.out.println("XijieHardDisk get");
    }
}
