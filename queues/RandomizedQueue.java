import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int count;
    private int nullified;
    public RandomizedQueue() {                // construct an empty randomized queue
        queue = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count - nullified;
    }

    private void resize(int capacity) {
        assert capacity >= count;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            temp[i] = queue[i];
        }
        queue=temp;
    }

    public void enqueue(Item item) {
        if (count == queue.length) resize(2*queue.length);
        queue[count++] = item;
    }

    public Item dequeue() {
        int r;
        if (count == 0)
            throw new java.util.NoSuchElementException();
        while (true) {
            r = StdRandom.uniform(0,count);
            if (queue[r] != null) break;
        }
        Item discharge = queue[r];
        queue[r] = null;
        nullified++;
        return discharge;
    }

    public Item sample() {
        int r;
        if (count == 0)
            throw new java.util.NoSuchElementException();
        while (true) {
            r = StdRandom.uniform(0,count);
            if (queue[r] != null) break;
        }

        return queue[r];
    }

    public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item> {
        int i;

        public MyIterator() {
            i = 0;
            StdRandom.setSeed(System.currentTimeMillis());
            System.out.println(StdRandom.getSeed());
            StdRandom.shuffle(queue, 0, count-1);

            System.out.println(queue[0]);
        }

        public boolean hasNext() {
            while (i < count && queue[i] == null)
                i++;
            if (i >= count)
                return false;
            else
                return true;
        }

        public Item next() {
            if (hasNext()) {
                i++;
                return queue[i-1];
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {  // unit testing
        RandomizedQueue rq = new RandomizedQueue();

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
//        System.out.println(rq.dequeue());
//        System.out.println(rq.dequeue());
        Iterator iter1 = rq.iterator();
        Iterator iter2 = rq.iterator();

        System.out.println(iter1.next());
        System.out.println(iter1.next());
        System.out.println(iter1.next());
        System.out.println(iter1.next());
        System.out.println(iter1.next());
        System.out.println("Iter2 results");
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
        System.out.println(iter2.next());
    }
}