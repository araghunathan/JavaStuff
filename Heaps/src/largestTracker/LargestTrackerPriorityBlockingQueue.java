package largestTracker;
// LargestTrackerPriorityBlockingQueue.java



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Archana Raghunathan on 09/27/2015.
 */
public class LargestTrackerPriorityBlockingQueue {

    private static LargestTrackerPriorityBlockingQueue mInstance;
    private PriorityBlockingQueue<Integer> mQueue;

    public LargestTrackerPriorityBlockingQueue(){
        Comparator comparator = new IntegerComparator();
        //per the API docs (http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/PriorityBlockingQueue.html,
        //the default initial capacity for the empty constructor is 11 so I have put 11 in the constructor of this queue
        mQueue = new PriorityBlockingQueue<>(11, comparator);
    }

    /**
     * Guarantees the creation of a single instance across the virtual machine.
     * Assumed to be called very frequently.
     *
     * @return an instance of LargestTracker
     */
    static LargestTrackerPriorityBlockingQueue getInstance(){
        if(mInstance == null){
            mInstance = new LargestTrackerPriorityBlockingQueue();
        }
        return mInstance;
    }

    /**
     * Returns a list in O(n log m) time OR BETTER where n is the number of entries
     * added to LargestTracker and m is numberOfTopLargestElements. Duplicates are allowed
     *
     * @param numberOfTopLargestElements
     *            the number of top-most-elements to return
     * @return the top-most-elements in the tracker sorted in ascending order
     */
    List<Integer> getNLargest(int numberOfTopLargestElements){
        synchronized(this) {
            if (numberOfTopLargestElements < 0 || numberOfTopLargestElements > mQueue.size())
                throw new IllegalArgumentException();

            List<Integer> nLargest = new ArrayList<Integer>();
            //this could be an expensive series of operations, as the largest elements are removed from the priority queue
            //followed by adding them back to the same queue so that other operations can get the same data,
            //involving heapifying of the elements
            for (int i = 0; i < numberOfTopLargestElements; i++) {
                nLargest.add(0, mQueue.remove());
            }
            for(Integer i : nLargest){
                mQueue.add(i);
            }

            return nLargest;
        }
    }

    /**
     * Adds an entry to the tracker. This method must operate in O(log n) time
     * OR BETTER
     * @param anEntry
     *            the entry to add to the tracker. Entries need not be unique.
     */

    void add(int anEntry){
        mQueue.add(anEntry);
    }

    /**
     * Removes all the entries from the tracker. This should return in constant
     * time.
     */
    void clear(){
        mQueue.clear();
    }

    //overrode the compare function so that the values are sorted in descending order (higher to lower) rather than the default ascending (natural) order
    private class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer int1, Integer int2) {
            if(int1 < int2) {
                return 1;
            }
            else if(int1 > int2){
                return -1;
            }
            return 0;
        }
    }
}// end LargestTrackerPriorityBlockingQueue.java
