public class TestExercise3 {
    public static void main(String[] args) {
        System.out.println("=== Testing Exercise 3 - Decorator Pattern ===");

        System.out.println("\n--- Testing with ArrayList ---");
        LoggingDecorator loggedArrayList = new LoggingDecorator(MyList.getList(ListType.Array));
        loggedArrayList.add(5);
        loggedArrayList.add(10);
        loggedArrayList.add(15);
        System.out.println("Retrieved value at index 1: " + loggedArrayList.get(1));

        System.out.println("\n--- Testing with LinkedList ---");
        LoggingDecorator loggedLinkedList = new LoggingDecorator(MyList.getList(ListType.LinkedList));
        loggedLinkedList.add(100);
        loggedLinkedList.add(200);
        System.out.println("Retrieved value at index 0: " + loggedLinkedList.get(0));

        System.out.println("\n--- Testing with SynchronizedList ---");
        LoggingDecorator loggedSyncList = new LoggingDecorator(MyList.getList(ListType.SyncList));
        loggedSyncList.add(42);
        loggedSyncList.add(84);
        System.out.println("Retrieved value at index 1: " + loggedSyncList.get(1));

        System.out.println("\n--- Testing nested decorators ---");
        MyList baseList = MyList.getList(ListType.Array);
        LoggingDecorator decorator1 = new LoggingDecorator(baseList);
        LoggingDecorator decorator2 = new LoggingDecorator(decorator1);
        System.out.println("Adding to nested decorators:");
        decorator2.add(999);

        System.out.println("Exercise 3 tests completed successfully!");
    }
}