package com.home;

import java.util.List;

public class Main2 {

    public static void main(String[] args) {
	// write your code here
        test1();
        test2();
    }

    private static void test1(){
        System.out.println("\nThread 1");
        RetrievingKEntries tracker = RetrievingKEntries.getInstance();
        for(int i = 0; i < 200; i++){
            tracker.add((int) (Math.random() * 2000));
        }
        List<Integer> largestElements = tracker.getNLargest(50);
        for(Integer i : largestElements){
            System.out.print(i + ", ");
        }
    }

    private static void test2(){
        System.out.println("\nThread 2");
        RetrievingKEntries tracker2 = RetrievingKEntries.getInstance();
        for(int i = 0; i < 10; i++){
            tracker2.add((int) (Math.random() * 15000));
        }

        List<Integer> largestElements2 = tracker2.getNLargest(5);
        for(Integer i : largestElements2){
            System.out.print(i + ", ");
        }
    }

}
