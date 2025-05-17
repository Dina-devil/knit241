package org.knit;

import org.junit.jupiter.api.Test;
import org.knit.solutions.task18.DuplicateZeros;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateZerosTest18 {

    DuplicateZeros dz = new DuplicateZeros();

    @Test
    void testNormalCase() {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        long start = System.currentTimeMillis();
        dz.duplicateZeros(arr);
        long end = System.currentTimeMillis();

        System.out.println("\ntestNormalCase → ВВП: " + (end - start) + " мс");
        System.out.println("Результат: " + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 0, 0, 2, 3, 0, 0, 4}, arr);
    }

    @Test
    void testNoZeros() {
        int[] arr = {1, 2, 3};
        long start = System.currentTimeMillis();
        dz.duplicateZeros(arr);
        long end = System.currentTimeMillis();

        System.out.println("\ntestNoZeros → ВВП: " + (end - start) + " мс");
        System.out.println("Результат: " + Arrays.toString(arr));

        assertArrayEquals(new int[]{1, 2, 3}, arr);
    }

    @Test
    void testAllZeros() {
        int[] arr = {0, 0, 0};
        long start = System.currentTimeMillis();
        dz.duplicateZeros(arr);
        long end = System.currentTimeMillis();

        System.out.println("\ntestAllZeros → ВВП: " + (end - start) + " мс");
        System.out.println("Результат: " + Arrays.toString(arr));

        assertArrayEquals(new int[]{0, 0, 0}, arr);
    }

    @Test
    void testEdgeZeros() {
        int[] arr = {0, 1, 2, 3, 0};
        long start = System.currentTimeMillis();
        dz.duplicateZeros(arr);
        long end = System.currentTimeMillis();

        System.out.println("\ntestEdgeZeros → ВВП: " + (end - start) + " мс");
        System.out.println("Результат: " + Arrays.toString(arr));

        assertArrayEquals(new int[]{0, 0, 1, 2, 3}, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        long start = System.currentTimeMillis();
        dz.duplicateZeros(arr);
        long end = System.currentTimeMillis();

        System.out.println("\ntestEmptyArray → ВВП: " + (end - start) + " мс");
        System.out.println("Результат: " + Arrays.toString(arr));

        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testBigArr() {
        int[] arr = new int[10000000];
        Arrays.fill(arr, 1);
        arr[0] = 0;
        arr[1000000] = 0;
        arr[9000000] = 0;

        long start = System.currentTimeMillis();
        dz.duplicateZeros(arr);
        long end = System.currentTimeMillis();

        System.out.println("\ntestBigArr → ВВП: " + (end - start) + " мс");
        System.out.println("Результат (первые 10): " + Arrays.toString(Arrays.copyOf(arr, 10)));

        assertTrue(end - start < 2000, "Слишком долгое выполнение");
    }
}



