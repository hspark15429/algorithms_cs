import java.util.Iterator;
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue rq = new RandomizedQueue();
        
        int N = Integer.parseInt(args[0]);
        int count = 0;
        
        
        try {
            while (true && count < N) {
                rq.enqueue(StdIn.readString());
                count++;
            }
        } catch (java.util.NoSuchElementException e) { }
        
        Iterator iter = rq.iterator();
        for (Object str : rq) {
            System.out.println(str);
        }
    }
}