public class LoggingDecorator implements MyList {
    private final MyList list;

    public LoggingDecorator(MyList list) {
        this.list = list;
    }

    @Override
    public void add(int value) {
        list.add(value);
        System.out.println(value + " was added to the list");
    }

    @Override
    public int get(int index) {
        return list.get(index);
    }
}