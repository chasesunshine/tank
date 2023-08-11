package designMide.spring.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        Tank t = (Tank)context.getBean("tank");
        t.move();
    }
}
