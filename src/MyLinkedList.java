import java.util.Iterator;

public class MyLinkedList<T> {

    /**
     * Represents a Node of the list
     */
    private class Node{

        private T value;
        private Node next;

        public Node(T value, Node next){
            this.value = value;
            this.next = next;
        }
        public Node(T value){
            this.value = value;
            this.next = null;
        }
    }

    /**
     * Constructor for the list
     */
    public MyLinkedList(){
        this.head = null;
    }

    /**
     * Constructor for the list
     * @param list Elements that are added to the current list
     */
    public MyLinkedList(MyLinkedList list){
        this.head = null;
        addAll(list);
    }

    /**
     * Represents the head of the list
     */
    private Node head;
    private int size;

    /**
     * Appends the specified element to the end of this list.
     * @param value Value of the new Node
     * @return True if the appending was successful, false otherwise
     */
    public boolean add(T value){
        if(head == null){
            head = new Node(value, null);
            size++;
            return true;
        }
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        Node newNode = new Node(value, null);
        if(newNode == null) return false;
        current.next = newNode;
        size++;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index Index in the list
     * @param value Value of the new Node
     */
    public void add(int index, T value){
        // Cant be connected to the list
        if(index > size()) return;
        int count = 0;
        Node current = head;
        while(count != index - 1){
            count++;
            current = current.next;
        }
        Node newNode = new Node(value, current.next);
        current.next = newNode;
        size++;
    }

    /**
     *
     * @param list List to be added to the current list
     * @return Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.
     */
    public boolean addAll(MyLinkedList list){
        if(list == null) return false;
        Node current = list.head;
        while(current != null){
            add(current.value);
            current = current.next;
        }
        return true;
    }

    /**
     *
     * @param index Index in the list
     * @param list List to be added to the current list
     * @return Inserts all of the elements in the specified collection into this list, starting at the specified position.
     */
    public boolean addAll(int index, MyLinkedList list){
        if(list == null || index > size()) return false;
        Node current = list.head;
        while(current != null){
            add(index++, current.value);
            current = current.next;
        }
        return true;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     * @param value Value of the new Node
     */
    public void addFirst(T value){
        Node newNode = new Node(value, head);
        head = newNode;
        size++;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param value Value of the new Node
     */
    public void addLast(T value){
        add(value);
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear(){
        head = null;
    }

    /**
     *
     * @return Returns a shallow copy of this list
     */
    public MyLinkedList clone(){
        return this;
    }

    /**
     *
     * @param value Value of the Node to find in the list
     * @return True if the value is in this list, false otherwise
     */
    public boolean contains(T value){
        Node current = head;
        while(current != null){
            if(current.value.equals(value)) return true;
            current = current.next;
        }
        return false;
    }

    /**
     *
     * @return Return an Iterator over the elements in reversed order
     */
    public Iterator<T> descendingIterator(){
        Node tail = reverseList(head);
        return iterator(tail);
    }

    /**
     *
     * @return Retrieves, but does not remove, the head (first element) of this list.
     */
    public T element(){
        return head.value;
    }

    /**
     *
     * @return Returns the middle Node, or the right middle node if the length is even
     */
    public Node findMiddleNode(){
        return findMiddle();
    }

    /**
     *
     * @return Returns the value of the middle Node, or the right middle node if the length is even
     */
    public T findMiddleValue(){
        return findMiddle().value;
    }

    /**
     *
     * @param index Index of the element
     * @return Returns the element at the specified position in this list, null if the index is bigger than the size of the list
     */
    public T get(int index){
        if(index >= size()) return null;
        Node current = head;
        int counter = 0;
        while(current != null){
            if(counter == index){
                return current.value;
            }
            counter++;
            current = current.next;
        }
        return null;
    }

    /**
     *
     * @return Returns a number, if the list represents a binary number, -1 otherwise
     */
    public int getDecimalValue() {
        String binary = "";
        Node current = this.head;
        while(current != null){
            try{
                if(!current.value.equals(0) && !current.value.equals(1)){
                    System.out.println("list is only allowed to contain 0's and 1's");
                    return -1;
                }
                binary += current.value;
                current = current.next;
            }catch(IllegalArgumentException e){
                System.out.println("list is only allowed to contain 0's and 1's");
                return -1;
            }

        }
        return Integer.parseInt(binary, 2);
    }

    /**
     *
     * @return Returns the first element in this list.
     */
    public T getFirst(){
        return element();
    }

    /**
     *
     * @return Returns the last element in this list.
     */
    public T getLast(){
        Node current = head;
        while(current != null)
            current = current.next;
        return current.value;
    }

    /**
     *
     * @param value Value to be searched for
     * @return Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(T value){
        int count = 0;
        Node current = head;
        while(current != null){
            if(current.value == value) return count;
            count++;
            current = current.next;
        }
        return -1;
    }

    /**
     *
     * @param value Value to be searched for
     * @return Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    public int lastIndexOf(T value){
        int ans = -1;
        int count = 0;
        Node current = head;
        while(current != null){
            if(current.value == value) ans = count;
            count++;
            current = current.next;
        }
        return ans;
    }

    /**
     *
     * @param index Index where to start the iterator
     * @return Returns a list-iterator of the elements in this list (in proper sequence), starting at the specified position in the list.
     */
    public Iterator<T> listIterator(int index){
        if(index >= size()) return null;
        int count = 0;
        Node current = head;
        while(count < index){
            count++;
            current = current.next;
        }
        return iterator(current);
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     * @param value Value of the new Node
     */
    public void offer(T value){
        addLast(value);
    }

    /**
     * Inserts the specified element at the front of this list.
     * @param value Value of the new Node
     */
    public void offerFirst(T value){
        addFirst(value);
    }

    /**
     * Inserts the specified element at the end of this list.
     * @param value Value of the new Node
     */
    public void offerLast(T value){
        addLast(value);
    }

    /**
     *
     * @return Retrieves, but does not remove, the head (first element) of this list.
     */
    public T peek(){
        return head.value;
    }

    /**
     *
     * @return Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
     */
    public T peekFirst(){
        return peek();
    }

    /**
     *
     * @return Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
     */
    public T peekLast(){
        return getLast();
    }

    /**
     *
     * @return Retrieves and removes the head (first element) of this list.
     */
    public T poll(){
        T ans = head.value;
        head = head.next;
        size--;
        return ans;
    }

    /**
     *
     * @return Retrieves and removes the first element of this list, or returns null if this list is empty.
     */
    public T pollFirst(){
        return poll();
    }

    /**
     *
     * @return Retrieves and removes the last element of this list, or returns null if this list is empty.
     */
    public T pollLast(){
        if(head == null) return null;
        if(head.next == null){
            T ans = head.value;
            head = null;
            size--;
            return ans;
        }
        Node current = head;
        while(current.next.next != null){
            current = current.next;
        }
        T ans = current.next.value;
        current.next = null;
        size--;
        return ans;
    }

    /**
     *
     * @return Pops an element from the stack represented by this list.
     */
    public T pop(){
        return pollFirst();
    }

    /**
     * Pushes an element onto the stack represented by this list.
     * @param value Value of the new Node
     */
    public void push(T value){
        addFirst(value);
    }

    /**
     *
     * @return Retrieves and removes the head (first element) of this list.
     */
    public T remove(){
        return pollFirst();
    }

    /**
     *
     * @param index Index of the element in this list
     * @return Removes the element at the specified position in this list.
     */
    public T remove(int index){
        if(index >= size()) return null;
        if(index == 0) return remove();
        int count = 0;
        Node current = head;
        while(count < index - 1){
            count++;
            current = current.next;
        }
        T ans = current.next.value;
        current.next = current.next.next;
        size--;
        return ans;
    }

    /**
     *
     * @param value Value that should be removed
     * @return Removes the first occurrence of the specified element from this list, if it is present.
     */
    public boolean remove(T value){
        return remove(value, head);
    }

    /**
     *
     * @return Removes and returns the first element from this list.
     */
    public T removeFirst(){
        return remove();
    }

    /**
     *
     * @param value Value that should be removed
     * @return Removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
     */
    public boolean removeFirstOccurrence(T value){
        return remove(value);
    }

    /**
     *
     * @return Removes and returns the last element from this list.
     */
    public T removeLast(){
        return remove(size() - 1);
    }

    /**
     *
     * @param value Value that should be removed
     * @return Removes the last occurrence of the specified element in this list (when traversing the list from head to tail).
     */
    public boolean removeLastOccurrence(T value){
        Node tail = reverseList(head);
        boolean ans = remove(value, tail);
        this.head = reverseList(tail);
        return ans;
    }

    /**
     *
     * @param node Head of the list
     * @return The reversed list
     */
    public Node reverseList(Node node){
        if(node == null) return node;
        Node next = node.next;
        node.next = null;
        while(next != null){
            Node temp = next.next;
            next.next = node;
            node = next;
            next = temp;
        }
        return node;
    }

    /**
     *
     * @param index Index of the element in this list
     * @param value Value the node's value should be changed to
     * @return the element previously at the specified position, or null if there is no element at this position
     */
    public T set(int index, T value){
        if(index >= size()) return null;
        int count = 0;
        Node current = head;
        while(count < index){
            count++;
            current = current.next;
        }
        T ans = current.value;
        current.value = value;
        return ans;
    }

    /**
     *
     * @return Returns the size of the list
     */
    public int size(){
        return this.size;
    }

    /**
     *
     * @return Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     */
    public Object[] toArray(){
        Object[] arr = new Object[size()];
        Node current = head;
        int index = 0;
        while(current != null){
            arr[index++] = current.value;
            current = current.next;
        }
        return arr;
    }

    /**
     *
     * @param a The array to put the values in
     * @return Returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of the returned array is that of the specified array.
     */
    public T[] toArray(T[] a){
        int index = 0;
        Node current = head;
        while(index < a.length && current != null){
            a[index] = current.value;
            index++;
            current = current.next;
        }
        return a;
    }

    /**
     * Helper methods
     */

    /**
     *
     * @return Returns the middle Node, or the right middle node if the length is even
     */
    private Node findMiddle(){
        Node slow = this.head, fast = this.head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     *
     * @param node Start Node for the Iterator
     * @return An iterator over the list
     */
    private Iterator<T> iterator(Node node){
        Iterator<T> iter = new Iterator() {
            private Node current = node;
            @Override
            public boolean hasNext() {
                if(current == null) return false;
                return true;
            }

            @Override
            public Object next() {
                Node ans = current;
                current = current.next;
                return ans.value;
            }
        };
        return iter;
    }

    /**
     *
     * @param value Value that should be removed
     * @param head Head of the list
     * @return Removes the first occurrence of the specified element from this list, if it is present.
     */
    public boolean remove(T value, Node head){
        if(head.value == value){
            this.head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while(current.next != null){
            if(current.next.value == value){
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
