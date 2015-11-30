/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序，适用于数据服从均匀分布
 * 默认桶的大小是5
 * @author zhenhui
 */
public class Bucketsort {
    private static final int DEFAULT_BUCKET_SIZE = 5;
    
    public static void sort(int[] array) {
        sort(array, DEFAULT_BUCKET_SIZE);
    }
    
    public static void sort(int[] array, int bucketSize) {
        if (array.length == 0) {
            return;
        }
        
        // Determine minimum and maximum values
        int minValue = array[0];
        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minValue > array[i])
                minValue = array[i];
            if (maxValue < array[i])
                maxValue = array[i];
        }
        
        // Initialise buckets
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        
        // Distribute input array values into buckets
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
        }
        
        // Sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            // sort one bucket
            InsertionSort.sort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                array[currentIndex++] = bucketArray[j];
            }
        }
        
    }
    
    public static void show(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    
    // Test Method
//    public static void main(String[] args) {
//        int[] array = {2,7,0,1,4,6,5,3,2,10,-1,8};
//        sort(array);
//        show(array);
//    }
}
