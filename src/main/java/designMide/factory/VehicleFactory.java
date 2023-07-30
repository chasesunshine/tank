package designMide.factory;

/**
 * @author 10619
 */
public class VehicleFactory {
    /**
     * 简单工厂 - 可拓展性不好
     *
     * @return
     */
    public Car createCar(){
        return new Car();
    }

    public Broom createBroom(){
        return new Broom();
    }
}
