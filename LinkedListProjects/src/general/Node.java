package general;


import java.util.*;

/**
 * Created by Jhome on 10/9/2015.
 */
public class Node {
    List<Node> children;
    String value;
    HashMap<String,Node> graphVertexMapping;
    HashMap<String,List<String>> longestPathLists;

    public Node() {
        children = new LinkedList<>();
        if(graphVertexMapping == null)
            graphVertexMapping = new HashMap<>();
    }

    public void put(String value, String edge){
        if (graphVertexMapping.isEmpty()) {
            Node node = new Node();
            node.value = edge;
            node.children.add(node);
            graphVertexMapping.put(value,node);
        } else {
            if(graphVertexMapping.get(value) == null){
                Node node = new Node();
                node.value = edge;
                node.children.add(node);
                graphVertexMapping.put(value,node);
            } else {
                Node n = graphVertexMapping.get(value);
                Node node = new Node();
                node.value = edge;
                n.children.add(node);
                graphVertexMapping.put(value, n);
            }
        }
    }

    void printList(){
        longestPathLists = new HashMap<>();

        Set<String> keys = graphVertexMapping.keySet();
        for(String s: keys) {
            System.out.println("The starting key is " + s);
            List<Node> children = graphVertexMapping.get(s).children;
            Iterator it = children.iterator();
            String currPath = "";
            do {
                Node node = (Node) it.next();
                printNodeValues(node, currPath, s, s, 1);
            } while (it.hasNext());
                System.out.println("");
        }
    }

    private void printNodeValues(Node node, String currPath, String start, String s, int currPathLength){
        if(!s.equals(node.value)) {
            String currPair = "(" + s + "," + node.value + "," + currPathLength + ")";
            System.out.print(currPair);
            currPath = currPath.concat("(" + s + "," + node.value + ")");
            String path = start.concat(node.value);
            if(longestPathLists.get(path) == null){
                List<String> newList = new ArrayList<>();
                newList.add(currPath);
                longestPathLists.put(path, newList);
            }else{
                List<String> list = longestPathLists.get(path);
                String splitter = ",";
                if(currPathLength > (list.toString().split(splitter).length-1)){
                    list.clear();
                    list.add(currPath);
                    longestPathLists.put(path,list);
                }
            }
        }
        if(graphVertexMapping.containsKey(node.value)) {
            Node values = graphVertexMapping.get(node.value);
            if(values != null) {
                List<Node> children = values.children;
                Iterator valIterator = children.iterator();
                Node curr = (Node) valIterator.next();
                currPathLength++;
                printNodeValues(curr, currPath, start, node.value, currPathLength);
            }
        } else {
            currPath = "";
            System.out.println();
        }
    }

    public void printLongestPath(String mapping){
        if(longestPathLists.get(mapping) != null)
            System.out.println("The longest path for mapping " + mapping + " is " + longestPathLists.get(mapping));
    }
}
