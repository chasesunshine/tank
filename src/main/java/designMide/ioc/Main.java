package designMide.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ioc - 原来我们都是在程序中控制一个对象的产生，有了spring之后呢，交给spring配置文件里面来进行控制
 */
public class Main {
    public static void main(String[] args) {
//        Driver driver = new Driver();

        ApplicationContext context = new ClassPathXmlApplicationContext("app_ioc.xml");
//        Driver driver = (Driver) context.getBean("d");
        Tank tank = (Tank) context.getBean("tank");
    }
}
