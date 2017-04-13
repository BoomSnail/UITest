package com.xiaoshihua.thinkpad.uitest;

import java.util.LinkedList;

/**
 * Created by ThinkPad on 2016/9/5.
 */
public class SortTest {

    public static void main(String[] args) {
        int[] array = new int[]{
                34, 8, 45, 89, 19, 38, 46, 17, 28, 78, 90
        };
        int[] a = new int[]{
                8, 17, 19, 28, 34, 38, 45, 46, 78, 89, 90
        };
        ChooseSort(array);
        MaoPaoSort(array);
        System.out.println(DivideFind(a, 48));

        System.out.println(lastRemaining(3, 50));
        System.out.println(removeMN(50, 3));
    }

    public static int removeMN(int m, int n) {
        LinkedList linkedList = new LinkedList();
        for (int i = 1; i < m + 1; i++) {
            linkedList.add(new Integer(i));
        }
        int removed = -1;
        while (linkedList.size() > 1) {
            removed = (removed + n) % linkedList.size();
            linkedList.remove(removed--);
        }
        return ((Integer) linkedList.get(0)).intValue();
    }

    private static int lastRemaining(int m, int n) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }


    private static void MaoPaoSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    private static void ChooseSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int k = i;
            for (int j = i + 1; j < len; j++) {
                if (array[k] > array[j]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
        for (int i = 0; i < len; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static int DivideFind(int[] array, int value) {
        int len = array.length;
        int start = 0;
        int end = len - 1;
        while (start < end) {
            int middle = (start + end) / 2;
            if (array[middle] == value) {
                return middle;
            } else if (start > end) {
                return -1;
            } else if (array[middle] < value) {
                start = middle + 1;
            } else if (array[middle] > value) {
                end = middle - 1;
            }
        }
        return -1;
    }

}
