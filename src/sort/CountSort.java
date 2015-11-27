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
public class CountSort {
    
    public static void main(String[] args) {
        int[] nums = {2, 3, 3, 4, 1,3,4,3,1,2,2,1,2,4,3,4,4,2,3,4};
        sort(nums, 4);
        // 打印
        System.out.println(nums.length);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
    
    /**
     * 计数排序
     * @param R 数组中最大的元素
     */
    public static void sort(int[] nums, int R) {
        int[] count = new int[R+2];
        int[] aux = new int[nums.length];
        // 计算出现的频率
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] + 1]++;
        }
        // 将频率转化为索引
        for (int i = 0; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }
        // 将元素分类
        for (int i = 0; i < nums.length; i++) {
            aux[count[nums[i]]] = nums[i];
            count[nums[i]]++;
        }
        // 写回
        for (int i = 0; i < nums.length; i++) {
            nums[i] = aux[i];
        }
    }
}
