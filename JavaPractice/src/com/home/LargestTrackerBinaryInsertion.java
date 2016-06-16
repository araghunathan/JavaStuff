package com.home;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by  on 9/27/2015.
 */
public class LargestTrackerBinaryInsertion {

    private static LargestTrackerBinaryInsertion mInstance;
    private List<Integer> mOrderedList;
    private int mListSize;

    public LargestTrackerBinaryInsertion(){
        mOrderedList = Collections.synchronizedList(new ArrayList<>());
        mListSize = 0;
    }

    /**
     * Guarantees the creation of a single instance across the virtual machine.
     * Assumed to be called very frequently.
     *
     * @return an instance of LargestTracker
     */
    static LargestTrackerBinaryInsertion getInstance(){
        if(mInstance == null){
            mInstance = new LargestTrackerBinaryInsertion();
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
            if (numberOfTopLargestElements < 0 || numberOfTopLargestElements > mOrderedList.size())
                throw new IllegalArgumentException();

            List<Integer> nLargest = new ArrayList<>();
            for (int i = 0; i < numberOfTopLargestElements; i++) {
                nLargest.add(0, mOrderedList.get(mListSize - i - 1));
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
        int index = 0;
        if(mListSize > 0)
            index = ((mListSize % 2) == 1) ? (mListSize-1)/2 : mListSize/2;
        binaryInsertToOrderedList(anEntry, index);
        System.out.println("The list size is " + mListSize);
    }

    //this implements the binary search to determine where to insert the current item, operating in O(log n) for the average and worst case
    //the best case, which is O(1), occurs in the following situations:
    //     incoming entry is the first one added to the list
    //     incoming entry is equal to the entry at the initial index to search from (defined by currIndex)
    //     incoming entry is equal to or lesser than the very first list entry => incoming entry is appended to front of list
    //     incoming entry is equal to or greater than the very last list entry => incoming entry is appended to end of list
    private void binaryInsertToOrderedList(int anEntry, int currIndex){
        if(mListSize == 0){
            mOrderedList.add(anEntry);
        }else if(mOrderedList.get(0) >= anEntry) {
            mOrderedList.add(0, anEntry);
        }else if(mOrderedList.get(mListSize-1) <= anEntry) {
            mOrderedList.add(mListSize, anEntry);
        }else{
            int top = currIndex;
            // incoming entry is equal to the entry at the initial index to search from (defined by currIndex, reassigned to top here)
            if(anEntry == mOrderedList.get(top))
                mOrderedList.add(top,anEntry);
            else{
                //defines the bottom index to search from in the arraylist; if the incoming entry is lesser than the entry from the
                //current index, the bottom index starts at 0, indicating searching from the bottom half of the list. In this case,
                //the top index remains as is (starting out as the current index passed in, which is in the middle of the list)
                //otherwise, we know that the incoming entry is greater than the entry from the current index, so search in the top
                //half of the list, making the bottom index equal to the current index passed in, while the top is the very end of the list
                int bottom = anEntry < mOrderedList.get(top) ? 0 : currIndex;
                top = anEntry < mOrderedList.get(top) ? top : mListSize-1;

                //we have found the position to insert the incoming value if the incoming value is between the current and prior indices;
                //search is over if this is true
                boolean foundPosition = mOrderedList.get(top-1) <= anEntry && anEntry <= mOrderedList.get(top);
                boolean searchUp = false;
                int oldTop;
                int numSearchs = 0;
                while(top > 0 && !foundPosition) {
                    oldTop = top;
                    top = searchUp ? bottom + (int) Math.floor((top-bottom) / 2) : (int)Math.floor(top/2);

                    if (top == 0 || top > mListSize-1) break;
                    if(anEntry > mOrderedList.get(top)){
                        bottom = top;
                        searchUp = true;
                        foundPosition = anEntry <= mOrderedList.get(top+1);
                        if(!foundPosition) top = oldTop;
                    }else{
                        searchUp = false;
                        foundPosition = mOrderedList.get(top-1) <= anEntry && anEntry <= mOrderedList.get(top);
                    }
                    System.out.println("The bottom index is now " + bottom + " and the top index is " + top);
                    numSearchs++;

                }
System.out.println("How many times did binary search occur " + numSearchs);
                if(anEntry > mOrderedList.get(top))
                    mOrderedList.add(top+1,anEntry);
                else
                    mOrderedList.add(top, anEntry);


                for(Integer i : mOrderedList){
                    System.out.print(i + ", ");
                }
                System.out.println();

            }
        }
        mListSize++;
    }

    /**
     * Removes all the entries from the tracker. This should return in constant
     * time.
     */
    void clear(){
        mOrderedList.clear();
        mListSize = 0;
    }
}
