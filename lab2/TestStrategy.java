import strategy.*;
import java.util.Arrays;

public class TestStrategy {
    public static void main(String[] args) {
        System.out.println("=== Exercise 1: Strategy Pattern Demo ===");

        // Test with Comparable interface
        Integer[] arr1 = {5, 2, 8, 1, 9, 3};
        System.out.println("Original array: " + Arrays.toString(arr1));

        MyCollections.sort(arr1);
        System.out.println("Sorted with Comparable: " + Arrays.toString(arr1));

        // Test with custom comparator
        Integer[] arr2 = {5, 2, 8, 1, 9, 3};
        MyCollections.sort(arr2, new IntegerAscendingComparator());
        System.out.println("Sorted with Comparator: " + Arrays.toString(arr2));

        // Test with descending comparator
        Integer[] arr3 = {5, 2, 8, 1, 9, 3};
        MyCollections.sort(arr3, new MyComparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                return t2.compareTo(t1);
            }
        });
        System.out.println("Sorted descending: " + Arrays.toString(arr3));

        System.out.println("Strategy Pattern test completed successfully!");
    }
}