package designMide.observer.v6;


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

        WakeUpEvent bed = new WakeUpEvent(System.currentTimeMillis(), "bed");
        for (Observer o:Observers) {
            o.actionWakeUp(bed);
        }
    }

    // 事件类
    class WakeUpEvent{
        long timeStamp;
        String loc;

        public WakeUpEvent(long timeStamp, String loc) {
            this.timeStamp = timeStamp;
            this.loc = loc;
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
