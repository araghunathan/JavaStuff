package com.home;

/**
 * Created by Jhome on 10/3/2015.
 */
public class ArrayListImplementation {
    private int[] arrayList;
    private int mCount;

    public ArrayListImplementation(){
        arrayList = new int[10];
        mCount = 0;
    }

    public boolean add(Integer i){
        if(mCount >= arrayList.length){
//            System.arraycopy()
        }
        return true;
    }
}
