package com.sort;

import org.junit.Test;

/**
 * 选择排序
 * 获取最小值的地址
 * @author caiwx
 * @date 2019年03月20日 9:41
 *
 */
public class SelectSort {

    public int[] selectSort(int[] target) {
        int len = target.length;
        for (int i = 0; i < len; i ++)  {
            int min = i;
            for (int j = i + 1; j < len; j ++) {
                if (target[min] > target[j]) {
//                    target[min] = target[j];
                    min = j;    //交换地址即可
                }
            }
            if (target[min] != target[i]) {
                int temp = target[min];
                target[min] = target[i];
                target[i] = temp;
            }
        }
        return target;
    }

    @Test
    public void test1() {
        int target[] = selectSort(new int[]{1, 3, 4, 5, 2, 3, 9, 4, 5});
        for (int i : target) {
            System.out.println(i);
        }
    }
}
