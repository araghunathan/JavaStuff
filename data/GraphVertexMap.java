package com.home;

import java.util.*;

/**
 * Created by Jhome on 10/9/2015.
 */
public class GraphVertexMap {
    HashMap<String, List<Node>> locationVertices;

    public GraphVertexMap() {
        locationVertices = new HashMap<>();
    }

    public void put(String start, String end) {
        if (locationVertices.isEmpty()) {
            Node edge = new Node(start, end);
            List<Node> list = new ArrayList<>();
            list.add(edge);
            locationVertices.put(start, list);
        } else {
            if (locationVertices.get(start) == null) {
                Node edge = new Node(start, end);
                List<Node> list = new ArrayList<>();
                list.add(edge);
                locationVertices.put(start, list);
            } else {
                Node edge = new Node(start, end);
                List<Node> list = locationVertices.get(start);
                list.add(edge);
                locationVertices.put(start, list);
            }
        }
    }

    void printList(String start){
        Set<String> keys = locationVertices.keySet();
        String s = (String)keys.iterator().next();
        List<Node> values = locationVertices.get(s);
        Iterator it = values.iterator();
        Node node = (Node)it.next();
        printNodeValues(it, node, s);
        System.out.println();

/*
        for(String s: keys){

            System.out.println("Curr key is " + s);
            List<Node> values = locationVertices.get(s);
            Iterator it = values.iterator();
            Node node = (Node)it.next();
            printNodeValues(it, node, s);
            System.out.println();
        }
*/
    }

    private void printNodeValues(Iterator it, Node node, String s){
        System.out.print("(" + node.start + "," + node.end + ")");
        if(locationVertices.containsKey(node.end)) {
            List<Node> values = locationVertices.get(node.end);
            Iterator valIterator = values.iterator();
            Node curr = (Node)valIterator.next();
            printNodeValues(valIterator, curr, node.end);
            System.out.println();
        }
        if (it.hasNext())
            printNodeValues(it, (Node) it.next(), s);

//            System.out.println();
    //    }
    }

    class Node {
        String start,end;

        private Node(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }
}
