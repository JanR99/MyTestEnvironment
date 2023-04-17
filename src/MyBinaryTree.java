import java.util.*;

public class MyBinaryTree<T> {

    private class Node{
        private Node left;
        private Node right;
        private T value;

        public Node(Node left, Node right, T value){
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    private Node root;
    private Comparator<T> comp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Class oClass = o1.getClass();
            if(oClass == Integer.class || oClass == Character.class || oClass == Float.class ||
                    oClass == Short.class || oClass == Double.class || oClass == Byte.class || oClass == Long.class){
                int diff = (Integer)o1 - (Integer)o2;
                if(diff < 0) return -1;
                else if(diff > 0) return 1;
                else return 0;
            }else if(oClass == String.class){
                return ((String) o1).compareTo((String)o2);
            }else{
                return 0;
            }
        }

        @Override
        public boolean equals(Object obj) {
            return this.equals(obj);
        }
    };

    /**
     *
     * @param value Value that should be deleted
     * @return Returns true if @value was successfully deleted, false otherwise
     */
    public boolean delete(T value){
        return delete(value, root);
    }

    /**
     *
     * @param root
     * @param delNode
     * @return Returns true on successful deletion, false otherwise
     */
    public boolean deleteDeepest(Node root, Node delNode){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node tmp = null;
        while(!queue.isEmpty()){
            tmp = queue.peek();
            queue.remove();
            if(tmp.equals(delNode)){
                tmp = null;
                return true;
            }
            if(tmp.right != null){
                if(tmp.right.equals(delNode)){
                    tmp.right = null;
                    return true;
                }else{
                    queue.add(tmp.right);
                }
            }
            if(tmp.left != null){
                if(tmp.left.equals(delNode)){
                    tmp.left = null;
                    return true;
                }else{
                    queue.add(tmp.left);
                }
            }
        }
        return false;
    }

    /**
     * Inserts @value to this tree
     * @param value Value of the new Node
     */
    public void insert(T value){
        // Root of this tree is null
        if(root == null){
            root = new Node(null, null, value);
            return;
        }
        Node current = root;
        Node pred = root;
        // Iterate through the whole list
        while(current != null) {
            if (comp.compare(value, current.value) > 0) {
                // Save pred, if there are no more Nodes
                if (current.right == null)
                    pred = current;
                current = current.right;
            } else {
                // Save pred, if there are no more Nodes
                if (current.left == null)
                    pred = current;
                current = current.left;
            }
        }
        Node neu = new Node(null ,null, value);
        if(comp.compare(pred.value, value) < 0)
            pred.right = neu;
        else
            pred.left = neu;

    }

    /**
     * Prints the Nodes of this tree
     */
    public void printNode(){
        printNode(root);
    }

    /**
     * Prints the Nodes of this tree in preorder
     */
    public void preorder(){
        preorder(root);
    }

    /**
     * Prints the Nodes of this tree in inorder
     */
    public void inorder(){ inorder(root); }

    /**
     * Prints the Nodes of this tree in postorder
     */
    public void postorder(){
        postorder(root);
    }

    /**
     *
     * @param value Value to search for
     * @return Returns true if this tree contains @value, false otherwise
     */
    public boolean contains(T value){
        return contains(root, value);
    }


    /**
     * Helper Methods
     */

    /**
     *
     * @param value
     * @param node
     * @return True on successful deletion, false otherwise
     */
    private boolean delete(T value, Node node){
        if(root == null) return false;
        if(root.left == null && root.right == null){
            if(root.value.equals(value)){
                root = null;
                return true;
            }else{
                return false;
            }
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node tmp = null, keyNode = null;
        while(!queue.isEmpty()){
            tmp = queue.peek();
            queue.remove();
            if(tmp.value.equals(value))
                keyNode = tmp;
            if(tmp.left != null)
                queue.add(tmp.left);
            if(tmp.right != null)
                queue.add(tmp.right);
        }
        if(keyNode != null){
            T x = tmp.value;
            deleteDeepest(root, tmp);
            keyNode.value = x;
        }
        return false;
    }

    /**
     * Helper method for @contains(T value)
     * @param node
     * @param value
     * @return true if this tree contains @value, false otherwise
     */
    private boolean contains(Node node, T value){
        if(node == null)
            return false;
        if(node.value == value)
            return true;
        if(comp.compare(value, node.value) < 0 && contains(node.left, value))
            return true;
        if(comp.compare(value, node.value) > 0 && contains(node.right, value))
            return true;
        return false;
    }

    /**
     * Prints all Nodes in given order
     * @param node Current Node
     */
    private void preorder(Node node){
        if(node == null)
            return;
        System.out.println(node.value);
        preorder(node.left);
        preorder(node.right);
    }
    private void inorder(Node node){
        if(node == null)
            return;
        inorder(node.left);
        System.out.println(node.value);
        preorder(node.right);
    }
    private void postorder(Node node){
        if(node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.value);
    }

    /**
     * Prints all Values of this tree
     */
    private <T extends Comparable<?>> void printNode(Node root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }
    private <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        printWhitespaces(firstSpaces);
        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }
                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);
                printWhitespaces(i + i - 1);
                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);
                printWhitespaces(endgeLines + endgeLines - i);
            }
            System.out.println();
        }
        printNodeInternal(newNodes, level + 1, maxLevel);
    }
    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }
    private <T extends Comparable<?>> int maxLevel(Node node) {
        if (node == null)
            return 0;
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }
    private <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }

}
