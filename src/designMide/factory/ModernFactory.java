package designMide.factory;

public class ModernFactory extends AbstractFactory{

    @Override
    Food creatFood() {
        return new Bread();
    }

    @Override
    Vehicle creatVehicle() {
        return new Car();
    }

    @Override
    Weapon creatWeapon() {
        return new AK47();
    }
}
