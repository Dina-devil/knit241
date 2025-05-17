package org.knit;

import org.junit.Test;
import org.knit.solutions.task19.MergeArr;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergeArrTest19 {

    @Test
    public void testBasicMerge() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 3, nums2, 3);
        long end = System.nanoTime();

        System.out.println("\ntestBasicMerge → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат: " + Arrays.toString(nums1));
    }

    @Test
    public void testEmptyNums2() {
        int[] nums1 = {1};
        int[] nums2 = {};

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 1, nums2, 0);
        long end = System.nanoTime();

        System.out.println("\ntestEmptyNums2 → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат: " + Arrays.toString(nums1));
    }

    @Test
    public void testEmptyNums1() {
        int[] nums1 = {0};
        int[] nums2 = {1};

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 0, nums2, 1);
        long end = System.nanoTime();

        System.out.println("\ntestEmptyNums1 → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат: " + Arrays.toString(nums1));
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums1 = {-5, 0, 0, 0};
        int[] nums2 = {-7, -3};

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 1, nums2, 2);
        long end = System.nanoTime();

        System.out.println("\ntestNegativeNumbers → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат: " + Arrays.toString(nums1));
    }

    @Test
    public void testDuplicates() {
        int[] nums1 = {1, 2, 2, 0, 0};
        int[] nums2 = {2, 5};

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 3, nums2, 2);
        long end = System.nanoTime();

        System.out.println("\ntestDuplicates → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат: " + Arrays.toString(nums1));
    }

    @Test
    public void testAllElementsInNums2() {
        int[] nums1 = {0, 0, 0};
        int[] nums2 = {1, 2, 3};

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 0, nums2, 3);
        long end = System.nanoTime();

        System.out.println("\ntestAllElementsInNums2 → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат: " + Arrays.toString(nums1));
    }

    @Test
    public void testBigArr() {
        int[] nums1 = new int[200];
        int[] nums2 = new int[100];

        for (int i = 0; i < 100; i++) {
            nums1[i] = i * 2;
            nums2[i] = i * 2 + 1;
        }

        long start = System.nanoTime();
        new MergeArr().merge(nums1, 100, nums2, 100);
        long end = System.nanoTime();

        System.out.println("\ntestPerformance → ВВП: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Результат (первые 20): " + Arrays.toString(Arrays.copyOf(nums1, 20)));

        assertTrue("Слишком долгое выполнение", (end - start) < 1_000_000);

        for (int i = 0; i < 200; i++) {
            assertEquals(i, nums1[i]);
        }
    }
}




