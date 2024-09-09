class MyQueue<E> {
    public static final int INITIAL_CAPACITY = 3;
    private E[] data;
    private int size = 0;
    private int front = 0; // Used to track the front index for dequeue

    @SuppressWarnings("unchecked")
    public MyQueue() {
        data = (E[]) new Object[INITIAL_CAPACITY];
    }

    public void enqueue(E e) {
        if (size == data.length) {
            ensureCapacity();
        }
        data[(front + size) % data.length] = e;
        size++;
    }

    private void ensureCapacity() {
        E[] newData = (E[]) new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
    }

    public E dequeue() {
        if (size == 0) {
            return null;
        }
        E result = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return result;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % data.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

public class Solution {
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество операций: ");
        int numOperations = scanner.nextInt();

        for (int i = 0; i < numOperations; i++) {
            int operationType = scanner.nextInt();

            switch (operationType) {
                case 1: // Enqueue
                    String element = scanner.next();
                    queue.enqueue(element);
                    break;

                case 2: // Dequeue
                    String dequeuedElement = queue.dequeue();
                    if (dequeuedElement != null) {
                        System.out.println("Удалено: " + dequeuedElement);
                    } else {
                        System.out.println("Очередь пуста");
                    }
                    break;

                case 3: // toString
                    System.out.println(queue.toString());
                    break;

                default:
                    System.out.println("Неверный тип операции.");
                    break;
            }
        }

        System.out.println("Финальное состояние очереди:");
        System.out.println(queue.toString());

        scanner.close();
    }
}