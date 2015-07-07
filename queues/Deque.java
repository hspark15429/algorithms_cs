import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;

    private class Node {
        Item item;
        Node towardStart = null;
        Node towardEnd = null;
    }

    public Deque() {                          // construct an empty deque
        first = null;
        last = first;
        count = 0;
    }

    public boolean isEmpty() {                 // is the deque empty?
        if (first == null) {
            return true;
        }else {
            return false;
        }
    }

    public int size() {                       // return the number of items on the deque
        return count;
    }

    public void addFirst(Item item) {         // add the item to the front
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
        } else {

            Node oldfirst = first;

            first = new Node();
            first.item = item;
            first.towardEnd = oldfirst;

            oldfirst.towardStart = first;
        }

        count++;

    }

    public void addLast(Item item) {          // add the item to the end
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        if (last == null) {
            last = new Node();
            last.item = item;
            first = last;
        } else {

            Node oldlast = last;

            last = new Node();
            last.item = item;
            last.towardStart = oldlast;

            oldlast.towardEnd = last;
        }

        count++;
    }

    public Item removeFirst() {               // remove and return the item from the front
        if (size() == 0)
            throw new java.util.NoSuchElementException();

        count--;

        if (this.size() == 0) {
            Item item = first.item;
            first = null;
            last = null;

            return item;
        } else {

            Node oldfirst = first;
            first = first.towardEnd;
            first.towardStart = null;

            return oldfirst.item;
        }
    }

    public Item removeLast() {                // remove and return the item from the end
        if (size() == 0)
            throw new java.util.NoSuchElementException();

        count--;

        if (this.size() == 0) {
            Item item = first.item;
            first = null;
            last = null;

            return item;
        } else {

            Node oldlast = last;
            last = last.towardStart;
            last.towardEnd = null;

            return oldlast.item;
        }
    }

    public Iterator<Item> iterator() {        // return an iterator over items in order from front to end
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item> {
        Node current;

        public MyIterator() {
            current = first;
        }

        public boolean hasNext() {
            if (current != null) {
                return true;
            } else {
                return false;
            }
        }

        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.towardEnd;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main (String[] args) {
        Deque dq = new Deque();

        dq.addFirst(99);
        Iterator i = dq.iterator();

        System.out.println(dq.first.item);
    }


}


















