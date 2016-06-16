package com.home;

/**
 * Created by Jhome on 10/4/2015.
 */
public class DoublyLinkedList extends SinglyLinkedList {
    protected DoublyLinkedNode head;
    private DoublyLinkedNode middle;
    protected DoublyLinkedNode curr;
    private int numNodesAdvanced;

    public DoublyLinkedList() {
        head = new DoublyLinkedNode();
        head.value = 0;
        curr = head;
        middle = head;
        numNodesAdvanced = 0;
    }

    void add(int val) {
        numNodesAdvanced++;
        DoublyLinkedNode node = new DoublyLinkedNode();
        node.add(val);
        if (numNodesAdvanced % 2 == 0)
            middle = middle.next;
    }

    void printList() {
        int i = 0;
        DoublyLinkedNode node = head;
        while(i < numNodesAdvanced){
            System.out.println("The current node value is " + node.value);
            node = node.prev;
            i++;
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

    protected class DoublyLinkedNode extends Node{
        DoublyLinkedNode prev,next;
        int value;

        @Override
        void add(int value) {
            this.value = value;
            curr.next = this;
            curr.next.prev = curr;
            curr = this;
            head.prev = curr;
            curr.next = head;
        }

        @Override
        void delete() {
            this.value = this.next.value;
            this.next = this.next.next;
            this.next.prev = this;
            numNodesAdvanced--;
        }
    }
}