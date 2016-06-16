package com.home;

/**
 * Created by Jhome on 10/9/2015.
 */
public class SimpleHashTable {
    Node[] dictionaryEntries;
    int size;
    public SimpleHashTable(){
        dictionaryEntries = new Node[26];//{{0,
        size = dictionaryEntries.length;
    }

    public void get(String s){
        int hashIndex = (s.hashCode() & 0x7fffffff) % size;
        if(dictionaryEntries[hashIndex] == null){
            System.out.println("The string " + s + " is not found in the hash table");
        }else{
            Node curr = dictionaryEntries[hashIndex];
            while(curr != null){
                if(curr.value.equals(s)) {
                    System.out.println("We found " + s);
                    break;
                }
                curr = curr.next;
            }
        }
    }

    public void put(String s){
        computeHash(s);
    }

    public void computeHash(String s){
        int hashIndex = (s.hashCode() & 0x7fffffff) % size;
        System.out.println("The value is " + s + " and its hash is " + (s.hashCode() & 0x7fffffff) % size);
        if(dictionaryEntries[hashIndex] == null){
            System.out.println("Inserting the value " + s);
            dictionaryEntries[hashIndex] = new Node(hashIndex,s);
        }else{
            Node curr = dictionaryEntries[hashIndex];
            System.out.println("The current value is " + curr.value);
            if(s.compareTo(curr.value) < 0){
                Node newNode = new Node(hashIndex,s);
                newNode.next = curr;
                dictionaryEntries[hashIndex] = newNode;
            }else {
                boolean insertedNewVal = false;
                Node prev = curr;
                while (curr != null) {
                    System.out.println("Insert string " + s + " and curr string is " + curr.value);
                    if(s.compareTo(curr.value) < 0) {
                        System.out.println("Found it");
                        Node newNode = new Node(hashIndex, s);
                        newNode.next = curr;
                        prev.next = newNode;
                        insertedNewVal = true;
                        break;
                    }
                    System.out.println("The current value is " + curr.value);
                    prev = curr;
                    curr = curr.next;
                }

                if(!insertedNewVal) {
                    Node newNode = new Node(hashIndex, s);
                    prev.next = newNode;
                }
            }
        }
    }

    public void printHashTable(){
        for(int i = 0; i < dictionaryEntries.length; i++){
            Node n = dictionaryEntries[i];
            while(n != null) {
                System.out.println("The node key is " + n.key + " and node value is " + n.value);
                n = n.next;
            }
        }
    }

    private class Node{
        int key;
        String value;
        Node next;

        private Node(int key, String value){
            this.key = key;
            this.value = value;
        }
    }
}
