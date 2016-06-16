// RetrievingKEntries.java
package com.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Archana Raghunathan on 09/29/2015.
 */
public class RetrievingKEntries {

    private static RetrievingKEntries mInstance;
    private SortedMap<Integer,Integer> mTreeMap;
    private int m_numMapEntries;

    /* This implementation is using a synchronized tree map, which implements a red black tree (a type of binary search tree)
      with the sorted map interface - see http://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html*/
    private RetrievingKEntries(){
        Comparator comparator = new IntegerComparator();
        TreeMap<Integer,Integer> map = new TreeMap<>(comparator);
        mTreeMap = Collections.synchronizedSortedMap(map);
        m_numMapEntries = 0; //keeps track of the size (number of key-value pairs) stored in the tree map
    }

    /**
     * Guarantees the creation of a single instance across the virtual machine.
     * Assumed to be called very frequently.
     *
     * @return an instance of LargestTracker
     */
    static RetrievingKEntries getInstance(){
        if(mInstance == null){
            mInstance = new RetrievingKEntries();
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
        if(numberOfTopLargestElements < 0 || numberOfTopLargestElements > m_numMapEntries)
            throw new IllegalArgumentException();

        //this array will fill in the largest values from right-to-left, since the treemap is sorted according to
        //the largest elements. This way, instead of expensively pushing the elements one at a time into the front
        //of a list<integer> using the list.add(index,value) call, the elements can be inserted in true linear fashion
        int[] nLargestInAscending = new int[numberOfTopLargestElements];
        List<Integer> nLargestInAscendingList = new ArrayList<>(numberOfTopLargestElements);
        int currIndex = numberOfTopLargestElements-1;
        final Set<Integer> sortedKeys = mTreeMap.keySet();//this call should occur in linear time

        //this loop should occur in O(n) time (or less, depending on when we filled up the array)
        for(Integer i: sortedKeys) {
            int numOccurrencesCurrElement = mTreeMap.get(i);
            //while we still have more indices to fill and more occurrences of this element, continue to
            //populate the nlargest array
            while(currIndex > -1 && numOccurrencesCurrElement > 0){
                nLargestInAscending[currIndex] = i;
                currIndex--;
                numOccurrencesCurrElement--;
            }
            if(currIndex == -1) break;
        }

        //loops through the array of n largest elements in ascending order and adds them in the same order to the list to return - O(n)
        for(int j = 0; j < nLargestInAscending.length; j++){
            nLargestInAscendingList.add(nLargestInAscending[j]);
        }

        return nLargestInAscendingList;
    }

    /**
     * Adds an entry to the tracker. This method must operate in O(log n) time
     * OR BETTER
     * @param anEntry
     *            the entry to add to the tracker. Entries need not be unique.
     *
     *  per http://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html:
     *  "This implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations. Algorithms are adaptations of those in Cormen, Leiserson, and Rivest's Introduction to Algorithms."
     */
    void add(int anEntry){
        if(!mTreeMap.containsKey(anEntry))
            mTreeMap.put(anEntry,1);
        else
            mTreeMap.put(anEntry, mTreeMap.get(anEntry)+1);
        m_numMapEntries++;
    }

    /**
     * Removes all the entries from the tracker. This should return in constant
     * time.
     */
    void clear(){
        mTreeMap.clear();
        m_numMapEntries = 0;
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
}// end RetrievingKEntries.java