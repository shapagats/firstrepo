public class List {
    
    private int[] array;
    private int size;
    private static final int capacity = 10;

    public List() {
        array = new int[capacity];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }


    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }


    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = value;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(minCapacity, array.length * 2);
            int[] newArray = new int[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

 
    public static void main(String[] args) {
        List list = new List();

        list.add(45);
        list.add(20);
        list.add(30);

        list.add(1, 15);


        System.out.println("Element at index 2: " + list.get(2));

        list.set(2, 25);
        System.out.println("Element at index 2 after setting: " + list.get(2));

      
        list.remove(0);
        System.out.println("List after removing element at index 0:");
        list.display();

        System.out.println("List empty? " + list.isEmpty());

    
        System.out.println("Size : " + list.size());

        System.out.print("Elements: ");
        list.display();
    }
}
