/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.sort;

import std.StdRandom;

/**
 *
 * @author zhenhui
 */
public class Quick3way {
    
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)   return;
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable  v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else i++;
        }
        // 现在 a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
    
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    private static void print(Object[] a) {
        for (Object obj : a) {
            System.out.println(obj);
        }
    }
    
//    public static void main(String[] args) {
//        Comparable[] a = {2,0,5,4,1,9,8,3,6,7};
//        sort(a);
//        print(a);
//    }
}
