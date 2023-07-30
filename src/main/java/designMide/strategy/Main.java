package designMide.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int [] a = {9,2,3,5,7,1,4};

        Cat[] a = {new Cat(3,3),new Cat(1,1),new Cat(5,5)};
//        CatWeightComparator comparator = new CatWeightComparator();
        Sorter<Cat> sorter = new Sorter<>();

//        Dog[] a = {new Dog(3),new Dog(1),new Dog(5)};
//        DogComparator comparator = new DogComparator();
//        Sorter<Dog> sorter = new Sorter<>();

        sorter.sort(a,(o1,o2)->{
            if(o1.weight < o2.weight){
                return -1;
            } else if (o1.weight < o2.weight) {
                return 1;
            }else {
                return 0;
            }
        });
        System.out.printf(Arrays.toString(a));
    }
}
