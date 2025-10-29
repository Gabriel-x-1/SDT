package ex2;

import java.util.ArrayDeque;
import java.util.Deque;

public class CharacterPool {
    private final Deque<Character> pool = new ArrayDeque<>();
    private final int maxSize;

    public CharacterPool(int maxSize) {
        this.maxSize = Math.max(1, maxSize);
    }

    public synchronized Character acquire() {
        Character c = pool.pollFirst();
        if (c == null) c = new Character();
        return c;
    }

    public synchronized void release(Character c) {
        if (pool.size() < maxSize) {
            pool.addLast(c);
        }
        // else drop it for GC
    }

    public synchronized int size() { return pool.size(); }
}