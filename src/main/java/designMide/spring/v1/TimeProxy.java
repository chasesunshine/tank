package designMide.spring.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeProxy {

    public void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }

    public void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }

}


