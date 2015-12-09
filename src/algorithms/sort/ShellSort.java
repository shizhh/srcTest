/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.sort;

import std.StdOut;

/**
 *
 * @author zhenhui
 */
public class ShellSort {
    
    private ShellSort() {}
    
    public static void sort(Comparable[] a) {
        int N = a.length;
        
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        
        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    private static void exch(Object[] a, int i, int j) {
        Object obj = a[i];
        a[i] = a[j];
        a[j] = obj;
    }
    
    /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5,6,0,1,3,8,7,2,9,10};
        sort(a);
        show(a);
    }
    
}
