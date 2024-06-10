class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Stack {
    private Node top;
    private int size;
    private int maxSize;

    public Stack() {
        this.top = null;
        this.size = 0;
        this.maxSize = 0;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
        if (size > maxSize) {
            maxSize = size;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public int top() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
