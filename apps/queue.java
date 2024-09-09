class QueueNode {
    int data;
    QueueNode next;

    public QueueNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Queue {
    private QueueNode front;
    private QueueNode rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(int value) {
        QueueNode newNode = new QueueNode(value);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = front.data;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
        }
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Peek: " + queue.peek()); // Output: 10
        System.out.println("Dequeue: " + queue.dequeue()); // Output: 10
        System.out.println("Peek: " + queue.peek()); // Output: 20

        System.out.println("Is queue empty? " + queue.isEmpty()); // Output: false

        System.out.println("Dequeue: " + queue.dequeue()); // Output: 20
        System.out.println("Dequeue: " + queue.dequeue()); // Output: 30

        System.out.println("Is queue empty? " + queue.isEmpty()); // Output: true
    }
}
