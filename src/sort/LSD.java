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
public class LSD {
    
    private static final int BITS_PER_BYTE = 8;
    
    // do not instantiate
    private LSD() { }
    
    
    
    public static void sort(String[] a, int W) {
        // 通过前W个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        
        for (int d = W - 1; d >= 0; d--) {
            // 以下为一次计数排序
            int[] count = new int[R + 1];
            // 计算出现的频率
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // 将频率转化为索引
            for (int i = 0; i < count.length - 1; i++) {
                count[i + 1] += count[i];
            }
            // 将元素分类
            for (int i = 0; i < a.length; i++) {
                aux[count[a[i].charAt(d)]] = a[i];
                count[a[i].charAt(d)]++;
            }
            // 写回
            for (int i = 0; i < a.length; i++) {
                a[i] = aux[i];
            }
        }
    }
    
    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        int BITS = 32;                 // each int is 32 bits 
        int W = BITS / BITS_PER_BYTE;  // each int is 4 bytes
        int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        int MASK = R - 1;              // 0xFF

        int N = a.length;
        int[] aux = new int[N];

        for (int d = 0; d < W; d++) {         

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) {           
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == W-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int i = 0; i < N; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
//        String[] a = {"abcd","bcad","accc","aaaa","hhhh","bcad","caca"};
        int[] a = {256, 311, 33333, 1111114, 1,3,4,3,1,2,2,1,2,4,3,4,4,2,3,4};
        sort(a);
        // 打印
        System.out.println(a.length);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
