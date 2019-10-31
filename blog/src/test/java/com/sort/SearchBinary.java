package com.sort;

import org.junit.Test;

import java.util.*;

/**
 * 二分查找
 * @author caiwx
 * @date 2019年03月21日 14:48
 *
 */
public class SearchBinary {

    public int searchBinary(int[] target, int value) {

        int max = target.length - 1;
        int min = 0;

        while (max >= min) {
            int mid = (max + min) / 2;
            if (value == target[mid]) {
                return mid;
            } else if (value > target[mid]) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void test1() {
        System.out.println("index:" + searchBinary(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 3));
    }

    @Test
    public void test2() {
        int num = 10;
        System.out.println(num + "," + Integer.toBinaryString(num));
        num <<= 1;
        System.out.println(num + "," + Integer.toBinaryString(num));
        num <<= 1;
        System.out.println(num + "," + Integer.toBinaryString(num));

        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        /*for (Object i : arrayList) {
            System.out.println(i);
            arrayList.remove(i);
        }*/
        Iterator iterable = arrayList.iterator();
        while (iterable.hasNext()) {
            System.out.println(iterable.next());
            iterable.remove();
            System.out.println(iterable);
        }

        Vector vector = new Vector();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        for (Object i : vector) {
            System.out.println(i);
        }

        LinkedList linkedList = new LinkedList();
    }


}
