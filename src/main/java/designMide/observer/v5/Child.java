package designMide.observer.v5;


import java.util.ArrayList;
import java.util.List;

/**
 * 加入多个观察者
 */
public class Child {
    private boolean cry = false;
    List<Observer> Observers = new ArrayList<>();
    {
        Observers.add(new Dad());
        Observers.add(new Mum());
        Observers.add(new Dog());
    }

    public boolean isCry(){
        return cry;
    }

    public void wakeUp(){
        cry = true;
        for (Observer o:Observers) {
            o.actionWakeUp();
        }
    }

    interface Observer{
        void actionWakeUp();
    }

    class Dad implements Observer{
        public void feed(){
            System.out.println("dad feeding ... ");
        }

        @Override
        public void actionWakeUp() {
            feed();
        }
    }

    class Dog implements Observer{
        public void wang() {
            System.out.println("dog wang ...");
        }

        @Override
        public void actionWakeUp() {
            wang();
        }
    }

    class Mum implements Observer{
        public void hug() {
            System.out.println("mum hugging ...");
        }

        @Override
        public void actionWakeUp() {
            hug();
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }


}
