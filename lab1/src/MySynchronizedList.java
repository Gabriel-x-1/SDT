public class MySynchronizedList implements MyList {
    private final MyList list;

    public MySynchronizedList() {
        this.list = new MyArrayList();
    }

    @Override
    public synchronized void add(int value) {
        list.add(value);
    }

    @Override
    public synchronized int get(int index) {
        return list.get(index);
    }
}