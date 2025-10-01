public class TestExercise1 {
    public static void main(String[] args) {
        System.out.println("=== Testing Exercise 1 - Factory Method Pattern ===");

        MyList arrayList = MyList.getList(ListType.Array);
        arrayList.add(5);
        System.out.println("ArrayList - Added 5, get(0): " + arrayList.get(0));

        MyList linkedList = MyList.getList(ListType.LinkedList);
        linkedList.add(7);
        System.out.println("LinkedList - Added 7, get(0): " + linkedList.get(0));

        MyList syncList = MyList.getList(ListType.SyncList);
        syncList.add(9);
        System.out.println("SyncList - Added 9, get(0): " + syncList.get(0));

        System.out.println("\n=== Testing multiple additions ===");
        arrayList.add(10);
        arrayList.add(15);
        System.out.println("ArrayList - get(1): " + arrayList.get(1));
        System.out.println("ArrayList - get(2): " + arrayList.get(2));

        linkedList.add(20);
        linkedList.add(25);
        System.out.println("LinkedList - get(1): " + linkedList.get(1));
        System.out.println("LinkedList - get(2): " + linkedList.get(2));

        System.out.println("\n=== Testing bounds ===");
        try {
            arrayList.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ArrayList - Correctly threw exception for index 10: " + e.getMessage());
        }

        System.out.println("Exercise 1 tests completed successfully!");
    }
}