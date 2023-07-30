package designMide.factory;

/**
 *  形容词用 接口
 *  名词用 抽象类
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
