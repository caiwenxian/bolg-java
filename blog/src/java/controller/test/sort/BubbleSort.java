package controller.test.sort;

import org.junit.Test;

/**
 * 冒泡排序
 * @author caiwx
 * @date 2019年03月19日 15:56
 *
 */
public class BubbleSort {

    public int[] BubbleSort(int[] target) {
        int len = target.length;
        for (int i = 0; i < len; i ++) {
            boolean stop = false;
            for (int j = i; j < len; j ++) {
                if (target[i] > target[j]) {
                    int temp = target[i];
                    target[i] = target[j];
                    target[j] = temp;
                    stop = true;
                }
            }
            if (!stop) {
                break;
            }
        }
        return target;
    }

    @Test
    public void test1() {
        int[] str = {1, 3, 4, 2, 6, 2, 9, 6, 4, 10, 9};
        str = BubbleSort(str);
        for (int i : str) {
            System.out.print(i);
        }
    }
}
