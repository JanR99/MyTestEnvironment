import java.util.Iterator;

public class MyDoublyLinkedList<T> {
    
    //TODO test everything

    /**
     * First element of the list
     */
    private Node<T> head;
    /**
     * Last element of the list
     */
    private Node<T> tail;
    /**
     * Size of the list
     */
    private int size;

    /**
     * Represents a Node of the list
     * @param <T> Object-type of the list
     */
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

    /**
     * Constructor for the list
     */
    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructor for the list
     * @param list Elements that are added to the current list
     */
    public MyDoublyLinkedList(MyDoublyLinkedList<T> list) {
        this.head = list.head;
        this.tail = list.tail;
        this.size = list.size;
    }

    /**
     * Adds the given value to the list
     * @param value Value, that's going to be added to the current instance of MyDoublyLinkedList
     */
    public void add(T value) {
        if(head == null) { // list is empty
            head = new Node<>(value);
        } else if(tail == null) { // only head is in the list
            head.succ = new Node<>(value, head, null);
        } else { // list already has more than one item
            tail.succ = new Node<>(value, tail, null);
        }
        size++;
    }

    /**
     * Insert the given value at the specified position in this list
     * @param index Index in the list
     * @param value Value of the new Node
     * @return Returns true if the value was successfully added to the list, false otherwise
     */
    public boolean add(int index, T value) {
        if(index >= size || index < 0) return false;
        if(index == 0) { // Element is new head
            addFirst(value);
            return true;
        }
        if(index == size - 1) { // Element is new tail
            addLast(value);
            return true;
        }
        int i = 0;
        Node<T> current = head;
        while(current != null) {
            if(i == index) { // index found
                Node<T> newNode = new Node<>(value, current.prev, current.succ);
                current.prev.succ = newNode;
                current.succ.prev = newNode;
                return true;
            }
            i++;
            current = current.succ;
        }
        return false;
    }

    /**
     * Adds all Nodes of the given MyDoublyLinkedList to the current instance of MyDoublyLinkedList
     * @param list list, which elements should be added to the current instance of MyDoublyLinkedList
     */
    public void addAll(MyDoublyLinkedList<T> list) {
        Node<T> current = list.head;
        while(current != null) {
            add(current.value);
            current = current.succ;
        }
    }

    /**
     *
     * @param index Index in the list
     * @param list List to be added to the current list
     * @return Returns true if the list was successfully added to the current list, false otherwise
     */
    public boolean addAll(int index, MyDoublyLinkedList<T> list) {
        if(index >= size || index < 0) return false;
        Node<T> current = list.head;
        while(current != null) {
            if(!add(index++, current.value))
                return false;
            current = current.succ;
        }
        return true;
    }

    /**
     * Inserts the given value at the beginning of the current instance of MyDoublyLinkedList
     * @param value Value of the new Node
     */
    public void addFirst(T value) {
        head = new Node<>(value, null, head);
        size++;
    }

    /**
     * Appends the given value at the end ot the current instance of MyDoublyLinkedList
     * @param value Value of the new Node
     */
    public void addLast(T value) {
        tail = new Node<>(value, tail, null);
        size++;
    }

    /**
     * Removes all of the elements from this list
     */
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     *
     * @return Returns a shallow copy of this list
     */
    public MyDoublyLinkedList<T> clone() {
        return this;
    }

    /**
     *
     * @param value Value to be searched for
     * @return Returns true, if the given value exists in the current instance of the MyDoublyLinkedList, false otherwise
     */
    public boolean contains(T value) {
        Node<T> current = head;
        while(current != null) {
            if(current.value.equals(value)) return true;
            current = current.succ;
        }
        return false;
    }

    /**
     *
     * @return Returns an Iterator over the elements in reversed order
     */
    public Iterator<T> descendingIterator() {
        return (Iterator<T>) new Iterator<>() {
            Node<T> current = tail;

            @Override
            public boolean hasNext() {
                if(current == null) return false;
                return current.succ != null;
            }

            @Override
            public Object next() {
                return current.prev;
            }
        };
    }

    /**
     *
     * @return Retrieves, but does not remove the head (first element) of this list
     */
    public T element() {
        return head == null ? null : head.value;
    }

    /**
     *
     * @return Returns the value of the middle Node,
     * or the right middle node if the length is even
     */
    public T findMiddleValue() {
        int middle = size / 2 + 1;
        return getValueByIndex(middle);
    }

    public T get(int index) {
        if(index >= size || index < 0) return null;
        return getValueByIndex(index);
    }

    /**
     *
     * @return Returns a number, if the list represents a binary number, -1 otherwise
     */
    public int getDecimalValue() {
        StringBuilder binary = new StringBuilder();
        Node<T> current = this.head;
        while(current != null) {
            try {
                if(!current.value.equals(0) && !current.value.equals(1)) {
                    System.out.println("list is only allowed to contain 0's and 1's");
                    return -1;
                }
                binary.append(current.value);
                current = current.succ;
            } catch(IllegalArgumentException e) {
                System.out.println("list is only allowed to contain 0's and 1's");
                return -1;
            }
        }
        return Integer.parseInt(binary.toString(), 2);
    }

    /**
     *
     * @return Returns the first element of this list
     */
    public T getFirst() {
        return head.value;
    }

    /**
     *
     * @return Returns the last element of this list
     */
    public T getLast() {
        return tail.value;
    }

    /**
     *
     * @param value Value to be searched for
     * @return Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(T value) {
        int count = 0;
        Node<T> current = head;
        while(current != null) {
            if(current.value.equals(value)) return count;
            count++;
            current = current.succ;
        }
        return -1;
    }

    /**
     *
     * @param value Value to be searched for
     * @return Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
     */
    public int lastIndexOf(T value) {
        int ans = -1;
        int count = 0;
        Node<T> current = head;
        while(current != null) {
            if(current.value.equals(value)) ans = count;
            count++;
            current = current.succ;
        }
        return ans;
    }

    /**
     *
     * @param index Index where to start the iterator
     * @return Returns a list-iterator of the elements in this list (in proper sequence), starting at the specified position in the list.
     */
    public Iterator<T> listIterator(int index) {
        int count = 0;
        Node<T> tmp = head;
        while(tmp != null) {
            if(count == index) break;
            count++;
            tmp = tmp.succ;
        }
        Node<T> finalTmp = tmp;
        return (Iterator<T>) new Iterator<>() {
            Node<T> current = finalTmp;
            @Override
            public boolean hasNext() {
                return current == null ? false : current.succ != null;
            }

            @Override
            public Object next() {
                return current == null ? null : current.succ;
            }
        };
    }

    /**
     * Adds the specified element as the tail (last element) of this list.
     * @param value Value of the new Node
     */
    public void offer(T value) {
        addLast(value);
    }

    /**
     * Inserts the specified element at the front of this list.
     * @param value Value of the new Node
     */
    public void offerFirst(T value) {
        addFirst(value);
    }

    /**
     * Inserts the specified element at the end of this list.
     * @param value Value of the new Node
     */
    public void offerLast(T value) {
        addLast(value);
    }

    /**
     *
     * @return Retrieves, but does not remove, the head (first element) of this list.
     */
    public T peek() {
        return head == null ? null : head.value;
    }

    /**
     *
     * @return Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
     */
    public T peekFirst() {
        return peek();
    }

    /**
     *
     * @return Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
     */
    public T peekLast() {
        return getLast();
    }

    /**
     *
     * @return Retrieves and removes the head (first element) of this list.
     */
    public T poll() {
        if(head == null) return null;
        T ans = head.value;
        head = head.succ;
        size--;
        return ans;
    }

    /**
     *
     * @return Retrieves and removes the first element of this list, or returns null if this list is empty.
     */
    public T pollFirst() {
        return poll();
    }

    /**
     *
     * @return Retrieves and removes the last element of this list, or returns null if this list is empty.
     */
    public T pollLast() {
        if(tail == null) return null;
        T ans = tail.value;
        tail = tail.prev;
        return ans;
    }

    /**
     *
     * @return Pops an element from the stack represented by this list.
     */
    public T pop() {
        return pollFirst();
    }

    /**
     * Pushes an element onto the stack represented by this list.
     * @param value Value of the new Node
     */
    public void push(T value) {
        addFirst(value);
    }

    /**
     *
     * @return Retrieves and removes the head (first element) of this list.
     */
    public T remove() {
        //TODO
        return null;
    }

    /**
     *
     * @param index Index of the element in this list
     * @return Removes the element at the specified position in this list.
     */
    public T remove(int index) {
        if(index >= size || index < 0) return null;
        int count = 0;
        Node<T> current = head;
        while(current != null) {
            if(count == index) {
                if(current.prev != null) {
                    current.prev.succ = current.succ;
                }
                if(current.succ != null) {
                    current.succ.prev = current.prev;
                }
                return current.value;
            }
            current = current.succ;
        }
        return null;
    }

    /**
     *
     * @param value Value to be searched for
     * @return Returns true, if the value was successfully found in the current instance of the MyDoublyLinkedList and
     * removed from it, false otherwise
     */
    public boolean remove(T value) {
        Node<T> current = head;
        while(current != null) {
            if(current.value.equals(value)) {
                if(current == head) { // element is the head, so we change the head to the successor of the current head
                    head = head.succ;
                    return true;
                } else if(current == tail) { // element is the tail, so we change the tail to the predecessor of the current tail
                    tail = tail.prev;
                    return true;
                } else { // element is somewhere in the middle of the list
                    if(current.prev != null) {
                        current.prev.succ = current.succ;
                    }
                    if(current.succ != null) {
                        current.succ.prev = current.prev;
                    }
                }
                return true;
            }
            current = current.succ;
        }
        return false;
    }

    /**
     *
     * @return Removes and returns the first element from this list.
     */
    public T removeFirst() {
        return remove();
    }

    /**
     *
     * @param value Value that should be removed
     * @return Removes the first occurrence of the specified element in this list (when traversing the list from head to tail).
     */
    public boolean removeFirstOccurrence(T value) {
        return remove(value);
    }

    /**
     *
     * @return Removes and returns the last element from this list.
     */
    public T removeLast() {
        return remove(size - 1);
    }

    /**
     *
     * @param value Value that should be removed
     * @return Removes the last occurrence of the specified element in this list (when traversing the list from head to tail).
     */
    public boolean removeLastOccurrence(T value) {
        Node<T> current = tail;
        while(current != null) {
            if(value.equals(current.value)) {
                if(current.prev != null) {
                    current.prev.succ = current.succ;
                }
                if(current.succ != null) {
                    current.succ.prev = current.prev;
                }
                return true;
            }
            current = current.prev;
        }
        return false;
    }

    /**
     *
     * @param node Head of the list
     * @return The reversed list
     */
    public Node<T> reverseList(Node<T> node) {
        if(node == null) return null;
        Node<T> next = node.succ;
        node.succ = null;
        while(next != null){
            Node<T> temp = next.succ;
            next.succ = node;
            node = next;
            next = temp;
        }
        return node;
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

    /**
     *
     * @param index Index of the element in this list
     * @param value Value the node's value should be changed to
     * @return the element previously at the specified position, or null if there is no element at this position
     */
    public T set(int index, T value) {
        if(index >= size || index < 0) return null;
        Node<T> current = head;
        int count = 0;
        while(current != null) {
            if(count == index) {
                T ans = current.value;
                current.value = value;
                return ans;
            }
            current = current.succ;
        }
        return null;
    }

    /**
     *
     * @return Returns the size of the list
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> current = head;
        int index = 0;
        while(current != null) {
            arr[index++] = current;
            current = current.succ;
        }
        return arr;
    }

    /**
     *
     * @param a The array to put the values in
     * @return Returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of the returned array is that of the specified array.
     */
    public T[] toArray(T[] a) {
        int index = 0;
        Node<T> current = head;
        while(index < a.length && current != null) {
            a[index++] = current.value;
            current = current.succ;
        }
        return a;
    }

    /**
     *
     * @return Returns an instance of MyLinkedList representing the current instance of MyDoublyLinkedList
     */
    public MyLinkedList<T> toLinkedList() {
        MyLinkedList<T> list = new MyLinkedList<>();
        Node<T> current = head;
        while(current != null) {
            list.add(current.value);
            current = current.succ;
        }
        return list;
    }

    // Helper Methods //

    private Iterator<T> iterator(Node<T> node) {
        return (Iterator<T>) new Iterator() {
            private Node current = node;

            @Override
            public boolean hasNext() {
                return current.succ != null;
            }

            @Override
            public Object next() {
                Node ans = current;
                current = current.succ;
                return ans.value;
            }
        };
    }

    private T getValueByIndex(int index) {
        int i = 0;
        Node<T> current = head;
        while(current != null) {
            if(i == index) {
                return current.value;
            }
            i++;
            current = current.succ;
        }
        return null;
    }
}
