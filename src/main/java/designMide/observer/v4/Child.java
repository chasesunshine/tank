package designMide.observer.v4;

/**
 * 加入多个观察者
 */
public class Child {
    private boolean cry = false;
    private Dad dad = new Dad();
    private Mum mum = new Mum();
    private Dog dog = new Dog();

    public boolean isCry(){
        return cry;
    }

    public void wakeUp(){
        cry = true;
        dad.feed();
        dog.wang();
        mum.hug();
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }
}
