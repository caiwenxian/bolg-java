package com.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序
 * @author caiwx
 * @date 2019年03月21日 17:35
 *
 */
public class QuickSort {

    public int[] quickSort(int[] target) {
        if (target.length == 0) {
            return target;
        }
        quickSort(target, 0, target.length - 1);
        return target;
    }

    public void quickSort(int[] target, int low, int high) {
        if(low > high) {
            return;
        }
        int i = low;
        int j = high;
        int key = target[low];
        while(i < j) {
            while(i < j && target[j] > key) {
                j --;
            }
            while(i < j && target[i] <= key) {
                i ++;
            }
            if (i < j) {
                int temp = target[i];
                target[i] = target[j];
                target[j] = temp;
            }
        }
        int temp = target[low];
        target[low] = target[i];
        target[i] = temp;

        quickSort(target, low, i - 1);
        quickSort(target, i + 1 , high);

    }

    @Test
    public void test1() {
        int target[] = new int[]{1, 3, 4, 5, 2, 3, 9, 4, 5};
        target = quickSort(target);
        System.out.println(Arrays.toString(target));
    }
}
