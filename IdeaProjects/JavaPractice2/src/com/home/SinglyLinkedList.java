package com.home;

/**
 * Created by Jhome on 10/4/2015.
 */
public class SinglyLinkedList {
    private Node head;
    private Node middle;
    private Node curr;
    private int numNodesAdvanced;

    public SinglyLinkedList(){
        head = new Node();
        head.value = 0;
        curr = head;
        middle = head;
        numNodesAdvanced = 0;
    }

    void add(int val){
        numNodesAdvanced++;

        Node node = new Node();
        node.add(val);
        if(numNodesAdvanced % 2 == 0)
            middle = middle.next;
    }

    void printList(){
        for(Node node = head; node != null; node = node.next){
            System.out.println("The current node value is " + node.value);
        }
    }

    void deleteHead(){
         head.delete();
    }

    void deleteMiddleNode(){
        middle.delete();
    }

    void deleteTail(){
        curr.delete();
    }

    private class Node{
        Node next;
        int value;

        void add(int value){
            this.value = value;
            curr.next = this;
            curr = this;
        }

        void delete() {
            System.out.println("The node to delete is " + this.value);
            if (this.next != null){
                this.value = this.next.value;
                this.next = this.next.next;
            }else{
                Node start = middle;
                while(start.next.next != null){
                    start = start.next;
                }
                start.next = null;
                curr = start;
            }
            if (this == middle) {
                middle = this;
            }else if(this == head){
                head = this;
            }
        }
    }
}
