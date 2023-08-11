package designMide.observer.v7;


import java.util.ArrayList;
import java.util.List;

/**
 * 有很多时候，观察者需要根据事件的具体情况进行处理
 * 大多数时候，我们处理事件的时候，需要事件源对象
 * 事件也可以形成继承体系
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

        WakeUpEvent bed = new WakeUpEvent(System.currentTimeMillis(), "bed",this);
        for (Observer o:Observers) {
            o.actionWakeUp(bed);
        }
    }

    // 我们可以有很多不同的事件，事件本身有很多很多类型
    abstract class Event<T>{
        abstract T getSource();
    }

    // 事件类
    class WakeUpEvent extends Event<Child>{
        long timeStamp;
        String loc;
        Child source;

        public WakeUpEvent(long timeStamp, String loc,Child source) {
            this.timeStamp = timeStamp;
            this.loc = loc;
            this.source = source;
        }

        @Override
        Child getSource() {
            return source;
        }
    }

    interface Observer{
        void actionWakeUp(WakeUpEvent wakeUpEvent);
    }

    class Dad implements Observer{
        public void feed(){
            System.out.println("dad feeding ... ");
        }

        @Override
        public void actionWakeUp(WakeUpEvent wakeUpEvent) {
            feed();
        }
    }

    class Dog implements Observer{
        public void wang() {
            System.out.println("dog wang ...");
        }

        @Override
        public void actionWakeUp(WakeUpEvent wakeUpEvent) {
            wang();
        }
    }

    class Mum implements Observer{
        public void hug() {
            System.out.println("mum hugging ...");
        }

        @Override
        public void actionWakeUp(WakeUpEvent wakeUpEvent) {
            hug();
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }


}