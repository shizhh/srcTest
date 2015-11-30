/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author zhenhui
 * For fun
 */
public class Sleepsort {
    public static void sort(int[] array) {
        final CountDownLatch doneSignal = new CountDownLatch(array.length);
        for (final int num : array) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        doneSignal.countDown();
                        
                        //using straight milliseconds produces unpredictable
			//results with small numbers
			//using 1000 here gives a nifty demonstration
                        Thread.sleep(num * 1000);
                        System.out.println(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
    
    public static void main(String[] args) {
        int[] array = {2,7,0,1,4,6,5,3,9,2,10,8};
        sort(array);
    }
}
