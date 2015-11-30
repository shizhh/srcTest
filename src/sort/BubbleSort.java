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
public class BubbleSort {
    public static void sort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j, j - 1);
                }
            }
        }
    }

    private static void swap(Integer[] array, int a, int b) {
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public static void show(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    public static void main(String[] args) {
        Integer[] array = {2,7,0,1,4,6,5,3,9,2,10,-1,8};
        sort(array);
        show(array);
    }
}
