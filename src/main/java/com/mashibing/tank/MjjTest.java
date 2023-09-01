package com.mashibing.tank;

import java.util.ArrayList;
import java.util.List;

public class MjjTest {
    private static void test1(List<Number> number){

    }

    private static void test2(List<?extends Number> number){
        number.stream().forEach(
                number1 -> {
                    if(number1 instanceof TestNumber){
                        TestNumber number11 = (TestNumber) number1;
                        number11.test();
                    }
                }
        );

    }

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();
        List<TestNumber> testNumber = new ArrayList<>();
        test1(numbers);
//        test1(testNumber);

        test2(numbers);
        test2(testNumber);
    }
}
class TestNumber extends Number{

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    public Boolean test(){
        return true;
    }
}