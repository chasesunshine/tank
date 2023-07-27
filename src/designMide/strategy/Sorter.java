package designMide.strategy;

public class Sorter<T> {

    public void sort(T[] a,Comparator<T> comparator) {
        // 选择
        for (int i = 0; i < a.length - 1 ; i++) {
            int minPos = i;
            for (int j = i  + 1 ; j < a.length ; j++) {
                minPos = comparator.compare(a[j],a[minPos])== -1 ? j :minPos;
//                minPos = a[j].compareTo(a[minPos]) == -1 ? j :minPos;
            }
            swap(a, i, minPos);
        }

    }
    private void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }





    /*
    public void sort(int[] a) {

        //冒泡
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a.length - i - 1; j++) {
//                if(a[j] > a[j+1]){
//                    int temp = a[j];
//                    a[j] = a[j+1];
//                    a[j+1] = temp;
//                }
//            }
//        }

        // 选择
        for (int i = 0; i < a.length - 1 ; i++) {
            int minPos = i;
            for (int j = i  + 1 ; j < a.length ; j++) {
                minPos = a[j] < a[minPos] ? j :minPos;
            }
            swap(a, i, minPos);
        }

    }
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    */

}
