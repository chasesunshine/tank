package designMide.factory;

public class CarFactory{
    public Car create(){
        System.out.println("car created");
        return new Car();
    }
}
