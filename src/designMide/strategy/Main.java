package designMide.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int [] a = {9,2,3,5,7,1,4};

        Cat[] a = {new Cat(3,3),new Cat(1,1),new Cat(5,5)};
        CatWeightComparator comparator = new CatWeightComparator();
        Sorter<Cat> sorter = new Sorter<>();

//        Dog[] a = {new Dog(3),new Dog(1),new Dog(5)};
//        DogComparator comparator = new DogComparator();
//        Sorter<Dog> sorter = new Sorter<>();

        sorter.sort(a,comparator);
        System.out.printf(Arrays.toString(a));
    }
}
