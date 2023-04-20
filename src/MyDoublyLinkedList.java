public class MyDoublyLinkedList<T> {

    //TODO make comments to the methods

    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> succ;

        public Node(T value) {
            this.value = value;
            this.prev = null;
            this.succ = null;
        }

        public Node(T value, Node<T> prev, Node<T> succ) {
            this.value = value;
            this.prev = prev;
            this.succ = succ;
        }
    }

    public void add(T value) {
        if(head == null) { // list is empty
            head = new Node<>(value);
        } else if(tail == null) { // only head is in the list
            head.succ = new Node(value, head, null);
        } else { // list already has more than one item
            tail.succ = new Node(value, tail,null);
        }
    }

    public boolean remove(T value) {
        Node<T> current = head;
        while(current != null) {
            if(current.value.equals(value)) {
                return removeHelper(current);
            }
            current = current.succ;
        }
        return false;
    }

    public boolean remove(Node node) {
        Node<T> current = head;
        while(current != null) {
            if(current == node) {
                return removeHelper(current);
            }
            current = current.succ;
        }
        return false;
    }

    public boolean contains(T value) {
        Node<T> current = head;
        while(current != null) {
            if(current.value.equals(value)) return true;
            current = current.succ;
        }
        return false;
    }

    /**
     * Replaces all instances connected to oldValue with the newValue
     * @param oldValue value to be searched for
     * @param newValue value to be changed to
     * @return Returns true, if there was min. one element with oldValue, false otherwise
     */
    public boolean replace(T oldValue, T newValue) {
        Node<T> current = head;
        boolean ans = false;
        while(current != null) {
            if(current.value.equals(oldValue)) {
                current.value = newValue;
                ans = true;
            }
            current = current.succ;
        }
        return ans;
    }


    // Helper Methods //

    private boolean removeHelper(Node<T> current) {
        if(removeHeadOrTail(current, head, tail)) return true;
        if(current.prev != null) {
            current.prev.succ = current.succ;
        }
        if(current.succ != null) {
            current.succ.prev = current.prev;
        }
        return true;
    }

    private boolean removeHeadOrTail(Node<T> current, Node<T> head, Node<T> tail) {
        if(current == head) {
            head.succ = head;
            return true;
        }
        if(current == tail) {
            tail = tail.prev;
            return true;
        }
        return false;
    }
}
