public class MyArrayList implements MyList {
    private int[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    public MyArrayList() {
        this.array = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(int value) {
        if (size >= array.length) {
            resize();
        }
        array[size++] = value;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    private void resize() {
        int[] newArray = new int[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}