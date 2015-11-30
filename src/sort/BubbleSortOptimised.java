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
public class BubbleSortOptimised {
    public static void sort(Integer[] array) {
        int unsortedBelow = array.length;
        while (unsortedBelow != 0) {
            int lastSwap = 0;
            for (int i = 1; i < unsortedBelow; i++) {
                if (array[i - 1] > array[i]) {
                    swap(array, i, i - 1);
                    lastSwap = i;
                }
            }
            unsortedBelow = lastSwap;
        }
    }

    private static void swap(Integer[] array, int a, int b) {
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public static void show(Integer[] array) {
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }
    
    // Test Method
    public static void main(String[] args) {
        Integer[] array = {2,7,0,1,4,6,5,3,2,10,-1,8};
        sort(array);
        show(array);
    }
}
