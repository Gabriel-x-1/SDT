public interface MyList {
    void add(int value);
    int get(int index);

    static MyList getList(ListType type) {
        switch (type) {
            case Array:
                return new MyArrayList();
            case LinkedList:
                return new MyLinkedList();
            case SyncList:
                return new MySynchronizedList();
            default:
                throw new IllegalArgumentException("Unknown list type: " + type);
        }
    }
}