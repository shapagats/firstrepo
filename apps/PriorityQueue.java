public class PriorityQueue {
    private int[] range;
    private int size;
    private static final int capacity = 10;

    public PriorityQueue() {
        range = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        if (size == range.length - 1) {
            resize();
        }

        size++;
        int index = size;
        range[index] = value;
        bubbleUp(index);
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        int removedValue = range[1];
        range[1] = range[size];
        size--;
        bubbleDown(1);
        return removedValue;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return range[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void bubbleUp(int index) {
        while (index > 1 && range[index] > range[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void bubbleDown(int index) {
        int maxChildIndex;
        while (index <= size / 2) {
            maxChildIndex = maxChild(index);
            if (range[index] < range[maxChildIndex]) {
                swap(index, maxChildIndex);
                index = maxChildIndex;
            } else {
                break;
            }
        }
    }

    private int maxChild(int index) {
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);

        if (rightChildIndex > size) {
            return leftChildIndex;
        } else {
            return (range[leftChildIndex] > range[rightChildIndex]) ? leftChildIndex : rightChildIndex;
        }
    }

    private void swap(int i, int j) {
        int temp = range[i];
        range[i] = range[j];
        range[j] = temp;
    }

    private void resize() {
        int[] newHeap = new int[range.length * 2];
        System.arraycopy(range, 1, newHeap, 1, size);
        range = newHeap;
    }

    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int index) {
        return index * 2;
    }

    private int rightChild(int index) {
        return index * 2 + 1;
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();

        priorityQueue.insert(10);
        priorityQueue.insert(15);
        priorityQueue.insert(20);

        System.out.println("Peek: " + priorityQueue.peek());
        System.out.println("Remove: " + priorityQueue.remove());
        System.out.println("Peek: " + priorityQueue.peek()); 

        System.out.println("Is priority queue empty? " + priorityQueue.isEmpty());

        System.out.println("Remove: " + priorityQueue.remove()); 
        System.out.println("Remove: " + priorityQueue.remove());

        System.out.println("Is priority queue empty? " + priorityQueue.isEmpty()); 
    }
}
