package designMide.strategy;

// @FunctionalInterface 这个注解表示这是一个函数式接口
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1,T o2);

    /**
     * 为了解决该问题，JDK1.8中引入了一种新的机制：接口可以支持在声明方法的同时，提供实现。
     * 用到的方式就是在接口中定义默认方法和静态方法。
     * 实现含有默认方法或者静态方法的接口的类可以继承这些方法并直接使用，不再需要强制在实现类中去实现（重写）接口中的方法了。
     *
     * https://blog.csdn.net/lalala_dxf/article/details/123216972
     */
    default void m(){
        System.out.printf("m");
    }

    public static void test(){
        System.out.println("我是StaticMethod接口中的静态方法！");
    }
}
