package designMide.observer.v3;

/**
 * 加入观察者
 */
public class Child {
    private boolean cry = false;
    private Dad d = new Dad();

    public boolean isCry(){
        return cry;
    }

    public void wakeUp(){
        cry = true;
        d.feed();
    }

    /**
     * Dad是观察者，Child是被观察者
     */
    class Dad{
        public void feed(){
            System.out.println("dad feeding ... ");
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }
}
