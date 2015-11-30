/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author zhenhui
 */
public class Heapsort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int heapSize = array.length;
        buildHeap(array, heapSize);
        while (heapSize > 1) {
            swap(array, 0, heapSize - 1);
//            heapSize--;
            heapify(array, --heapSize, 0);
        }
    }
    
    /** 
     * 
     * 构造初始堆，从最后一个非叶子节点开始调整
     */
    private static <T extends Comparable<T>> void buildHeap(T[] array, int heapSize) {
        for (int i = (int)(array.length / 2); i >= 0; i--)
            heapify(array, heapSize, i);
    }

    /**
     * 
     * 调整为大顶堆
     */
    private static <T extends Comparable<T>> void heapify(T[] array, int heapSize, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        if (left < heapSize && array[left].compareTo(array[largest]) > 0)
            largest = left;
        if (right < heapSize && array[right].compareTo(array[largest]) > 0)
            largest = right;
        if (largest != i) {
            swap(array, i, largest);
            heapify(array, heapSize, largest);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] array, int i1, int i2) {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
    
    public static void show(Object[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    // Test Method
//    public static void main(String[] args) {
//        Integer[] array = {2,7,0,1,4,6,5,3,9,2,10,-1,8};
//        sort(array);
//        show(array);
//    }
}
