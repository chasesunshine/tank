package designMide.factory;

/**
 *  形容词用 接口   例如 - 飞机（plane）移动（实现 Moveable 接口）
 *  名词用 抽象类   例如 - 汽车（car）被创建 - 用交通工具（继承 Vehicle）
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ModernFactory();
        Vehicle car = abstractFactory.creatVehicle();
        car.go();
        Weapon ak47 = abstractFactory.creatWeapon();
        ak47.shoot();
        Food bread = abstractFactory.creatFood();
        bread.printName();
    }
}
