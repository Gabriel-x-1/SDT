package strategy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyCollectionsTest {

    @Test
    public void testSortComparableIntegers() {
        Integer[] arr = {5, 2, 8, 1, 9, 3};
        MyCollections.sort(arr);
        Integer[] expected = {1, 2, 3, 5, 8, 9};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortComparableStrings() {
        String[] arr = {"zebra", "apple", "cat", "dog", "bird"};
        MyCollections.sort(arr);
        String[] expected = {"apple", "bird", "cat", "dog", "zebra"};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortWithComparator() {
        Integer[] arr = {5, 2, 8, 1, 9, 3};
        MyCollections.sort(arr, new IntegerAscendingComparator());
        Integer[] expected = {1, 2, 3, 5, 8, 9};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortWithDescendingComparator() {
        Integer[] arr = {5, 2, 8, 1, 9, 3};
        MyCollections.sort(arr, new MyComparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2.compareTo(t1);
            }
        });
        Integer[] expected = {9, 8, 5, 3, 2, 1};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortEmptyArray() {
        Integer[] arr = {};
        MyCollections.sort(arr);
        assertEquals(0, arr.length);
    }

    @Test
    public void testSortSingleElement() {
        Integer[] arr = {42};
        MyCollections.sort(arr);
        Integer[] expected = {42};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testSortNullArray() {
        Integer[] arr = null;
        assertDoesNotThrow(() -> MyCollections.sort(arr));
    }

    @Test
    public void testSortWithNullComparator() {
        Integer[] arr = {5, 2, 8};
        assertDoesNotThrow(() -> MyCollections.sort(arr, null));
        Integer[] expected = {5, 2, 8};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testIntegerAscendingComparator() {
        IntegerAscendingComparator comparator = new IntegerAscendingComparator();
        assertTrue(comparator.compare(1, 2) < 0);
        assertTrue(comparator.compare(2, 1) > 0);
        assertEquals(0, comparator.compare(5, 5));
    }
}