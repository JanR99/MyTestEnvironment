import java.util.*;

//TODO rework almost everything

public class MyGraph {
    private class Node{
        String name;
        private Node(String name){
            this.name = name;
        }
    }
    public MyGraph(){
        map = new MyHashMap<>();
    }

    /**
     * Graph bestehend aus einem Node und dazugehörig alle erreichbaren Nodes
     * plus deren Gewicht
      */

    private MyHashMap<Node, MyHashMap<Node, Integer>> map;

    /**
     * Inserts the given Node into the graph without connection
     * @param name
     */
    public boolean add(Node name){
        if(contains(name)) return false;
        map.put(name, new MyHashMap<>());
        return true;
    }

    public void connect(Node from, Node to, int weight){
        if(!contains(from) || !contains(to)) return;
        MyHashMap cur = map.get(from);
        cur.put(to, weight);
    }

    public List getInwardEdges(Node to){
        List<Node> list = new LinkedList<>();
        for(Node n : map.keySet()){
            if(map.get(n).containsKey(to)) list.add(to);
        }
        return list;
    }

    public List getOutwardEdges(Node from){
        List<Node> list = new LinkedList<>();
        MyHashMap<Node, Integer> map = this.map.get(from);
        for(Node n : map.keySet()) list.add(n);
        return list;
    }

    public boolean remove(Node node){
        if(!map.containsKey(node)) return false;
        for(MyHashMap m : map.values()){
            m.remove(node);
        }
        map.remove(node);
        return true;
    }

    public int size(){
        return map.size();
    }

    /**
     *
     * @param node
     * @return Returns true if graph contains the given Node, false otherwise
     */
    private boolean contains(Node node){
        for(Node n : map.keySet())
            if(n.name.equals(node.name) || n.equals(node)) return true;
        return false;
    }


}
