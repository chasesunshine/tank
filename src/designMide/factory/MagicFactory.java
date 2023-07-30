package designMide.factory;

public class MagicFactory extends AbstractFactory{

    @Override
    Food creatFood() {
        return new MushRoom();
    }

    @Override
    Vehicle creatVehicle() {
        return new Broom();
    }

    @Override
    Weapon creatWeapon() {
        return new MagicStick();
    }
}
