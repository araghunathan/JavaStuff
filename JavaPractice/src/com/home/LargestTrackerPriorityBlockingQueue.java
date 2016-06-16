package com.home;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by  on 9/27/2015.
 */
public class LargestTrackerOld {

    private static LargestTrackerOld mInstance;
    private PriorityBlockingQueue<Integer> mQueue;

    public LargestTrackerOld(){
        Comparator comparator = new IntegerComparator();
        mQueue = new PriorityBlockingQueue<>(11, comparator);
    }

    /**
     * Guarantees the creation of a single instance across the virtual machine.
     * Assumed to be called very frequently.
     *
     * @return an instance of LargestTracker
     */
    static LargestTrackerOld getInstance(){
        if(mInstance == null){
            mInstance = new LargestTrackerOld();
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

    public void add(int anEntry){
        mQueue.add(anEntry);
    }

    /**
     * Removes all the entries from the tracker. This should return in constant
     * time.
     */
    void clear(){
        mQueue.clear();
    }

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

}
