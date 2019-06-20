package controller.test.sort;

import org.junit.Test;

/**
 * 插入排序
 * @author caiwx
 * @date 2019年03月20日 10:20
 *
 */
public class InsertSort {

    public int[] insertSort(int[] target) {
        int len = target.length;
        for (int i = 0; i < len; i ++) {
            for (int j = i - 1; j >= 0; j --) {
                if (target[j] > target[j + 1]) {
                    int tem = target[j];
                    target[j] = target[j + 1];
                    target[j + 1] = tem;
                } else {
                    break;
                }
            }
        }
        return target;
    }

    @Test
    public void test1() {
        int target[] = insertSort(new int[]{8, 1, 3, 4, 5, 2, 3, 9, 4, 5});
        for (int i : target) {
            System.out.println(i);
        }
    }
}
