public class MyStack<T> {
    MyLinkedList<T> stack;

    public MyStack() {
        stack = new MyLinkedList<>();
    }

    public boolean empty() {
        return stack.size() == 0;
    }

    public T peak() {
        return stack.getLast();
    }

    public T pop() {
        return stack.pop();
    }

    public T push(T element) {
        stack.push(element);
        return element;
    }

    public int search(Object o) {
        int count = 1;
        for(int i = stack.size() - 1; i >= 0; i--) {
            if(stack.get(i) == o) return count;
            count++;
        }
        return -1;
    }
}
