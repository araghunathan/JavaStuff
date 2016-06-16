package com.home;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

/*
        SinglyLinkedList list = new SinglyLinkedList();
        for(int i = 0; i < 15; i++) {
            list.add(i + 100);
        }
        list.deleteMiddleNode();
        list.printList();
        System.out.println("-------------------------------");
        list.deleteMiddleNode();
        list.printList();
        System.out.println("-------------------------------");
        list.deleteHead();
        list.printList();
        System.out.println("--------------------------------");
        list.deleteTail();
        list.printList();
        System.out.println("---------------------------------");

*/

        DoublyLinkedList list2 = new DoublyLinkedList();
        for(int i = 0; i < 15; i++) {
            list2.add(i + 100);
        }
        list2.deleteMiddleNode();
    //    list2.printList();

        SimpleHashTable hashTable = new SimpleHashTable();
        String[] values = new String[]{"Watermelon","Tangerine","Plum","Pineapple","Pear","Peach","Orange","Nectarine","Melon","Mango","Lemon","Kiwi","Grape","Cherry","Cantaloupe","Banana","Apple"};

        for(String s: values){
//            hashTable.put(s);
        }
//        hashTable.get("Tang");
  //      hashTable.printHashTable();

        GraphVertexMap graph = new GraphVertexMap();
        graph.put("A","B");
        graph.put("A","D");
        graph.put("A","C");

        graph.put("B","D");
//        graph.put("B","E");
//        graph.put("B","Z");
        graph.put("C","D");
 //       graph.put("C","E");
 //       graph.put("C","F");
        graph.put("D","E");
  //      graph.put("E","G");
//        graph.printList("A");
//        sortFunctions();

        Node graphList = new Node();
        graphList.put("A","B");
        graphList.put("A","D");
        graphList.put("A","C");
        graphList.put("B","D");
        graphList.put("B","E");
        graphList.put("B","F");
        graphList.put("C","D");
        graphList.put("C","E");
        graphList.put("C","F");
        graphList.put("C","G");
        graphList.put("D","E");
        graphList.put("E","F");
        graphList.put("E","K");
        graphList.put("C","Z");
        graphList.put("F","G");
        graphList.printList();
        graphList.printLongestPath("AE");
        graphList.printLongestPath("AG");
        graphList.printLongestPath("BG");
//        graphList.printList("A");
//        graphList.printList("B");
    }

    private static void printSortedList(int[] valuesToSort){
        for(int i = 0; i < valuesToSort.length; i++){
            System.out.print(valuesToSort[i] + ", ");
        }
        System.out.println();
    }

    private static void sortFunctions(){
        int[] valuesToSort = {10,9,8,7,6,5,4,3,2,1};
//        System.out.println("Values unsorted " + valuesToSort);

        //bubble sort
        int numTimesLooped = 0;

        for(int i = 0; i < valuesToSort.length-1; i++){
            for(int j = i+1; j < valuesToSort.length; j++){
                numTimesLooped++;
                if(valuesToSort[i] > valuesToSort[j]){
                    int temp = valuesToSort[i];
                    valuesToSort[i] = valuesToSort[j];
                    valuesToSort[j] = temp;
                }
            }
            numTimesLooped++;
        }
        System.out.println("What is the length of the list " + valuesToSort.length + " and num times looped in bubble sort " + numTimesLooped);
        printSortedList(valuesToSort);

        //selection sort
        valuesToSort = new int[]{10,9,8,7,6,5,4,3,2,1};
        int currentMin = Integer.MAX_VALUE;
        int currIndexToSwap = 0;
        numTimesLooped = 0;
        for(int i = 0; i < valuesToSort.length-1; i++){
            for(int j = i+1; j < valuesToSort.length; j++){
                numTimesLooped++;
                if(valuesToSort[j] < currentMin){
                    currentMin = valuesToSort[j];
                    currIndexToSwap = j;
                }
            }
            int temp = valuesToSort[i];
            valuesToSort[i] = currentMin;
            valuesToSort[currIndexToSwap] = temp;
            currentMin = Integer.MAX_VALUE;
            numTimesLooped++;
        }
        System.out.println("What is the length of the list " + valuesToSort.length + " and num times looped in selection sort " + numTimesLooped);
        printSortedList(valuesToSort);

        //insertion sort
        valuesToSort = new int[]{5,10,1,0,15,30,20,13,9};



        //merge sort - divide and conquer
        valuesToSort = new int[]{10,9,8,7,6,5,4,3,2,1};
        mergeSort();
        quickSort(valuesToSort, 0, valuesToSort.length-1);
        //heapsort
    }

    private static void mergeSort() {
        int[] valuesToSort = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] newSortedList = new int[valuesToSort.length];
        topDownMerge(valuesToSort,0,valuesToSort.length,newSortedList);
    }

    //https://en.wikipedia.org/wiki/Merge_sort
    private static void topDownMerge(int[] unsorted, int start, int end, int[] newSortedList){
        if((end - start) < 2) return;
        int mid = (int)Math.floor((start+end)/2);
        topDownMerge(unsorted, start, mid, newSortedList);
        topDownMerge(unsorted, mid, end, newSortedList);
        topDownSplitMerge(unsorted, start, mid, end, newSortedList);  // merge the two half runs
        for(int i = start; i < end; i++){
            unsorted[i] = newSortedList[i];
/*
        System.out.print(unsorted[i] + ", ");
*/
        }
/*
        System.out.println();
*/
    }

    private static void topDownSplitMerge(int[] unsorted, int start, int mid, int end, int[] newSortedList){
        int left = start, right = mid;
        for(int j = start; j < end; j++){
            if(left < mid && (right >= end || (unsorted[left] <= unsorted[right]))){
                newSortedList[j] = unsorted[left];
                left++;
            }else{
                newSortedList[j] = unsorted[right];
                right++;
            }
        }
    }

    private static void quickSort(int[] valuesToSort, int lower, int upper) {
        System.out.println("The lower is " + lower + " and the upper is " + upper);
        for(int i = 0; i < valuesToSort.length; i++){
            System.out.print(valuesToSort[i] + ", ");
        }
        System.out.println();
        if (lower < upper) {
            int p = partition(valuesToSort, lower, upper);
            quickSort(valuesToSort, lower, p-1);
            quickSort(valuesToSort, p+1, upper);
        }
    }

    private static int partition(int[] valuesToSort, int lower, int upper) {
        int pivot = valuesToSort[upper];
        int i = lower;
        for(int j = lower; j < upper; j++) {
            if (valuesToSort[j] <= pivot) {
                int temp = valuesToSort[i];
                valuesToSort[i] = valuesToSort[j];
                valuesToSort[j] = temp;
                i++;
            }
        }
        int temp = valuesToSort[i];
        valuesToSort[i] = valuesToSort[upper];
        valuesToSort[upper] = temp;
        return i;
    }
}
