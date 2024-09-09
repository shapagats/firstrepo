import java.util.Arrays;

public class Sort {

    // Generic method to sort an array of Comparable objects using selection sort
    public static <T extends Comparable<T>> void genericSort(T[] array) {
        int n = array.length;

        // Selection sort algorithm
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the current element
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    // Main method to test the genericSort method
    public static void main(String[] args) {
        // Test with array of integers
        Integer[] intArray = {5, 3, 8, 1, 2};
        System.out.println("Original Integer Array: " + Arrays.toString(intArray));
        genericSort(intArray);
        System.out.println("Sorted Integer Array: " + Arrays.toString(intArray));

        // Test with array of strings
        String[] strArray = {"banana", "apple", "orange", "grape"};
        System.out.println("\nOriginal String Array: " + Arrays.toString(strArray));
        genericSort(strArray);
        System.out.println("Sorted String Array: " + Arrays.toString(strArray));

        // Test with array of custom objects
        Person[] personArray = {
                new Person("John", 25),
                new Person("Alice", 30),
                new Person("Bob", 20)
        };
        System.out.println("\nOriginal Person Array: " + Arrays.toString(personArray));
        genericSort(personArray);
        System.out.println("Sorted Person Array: " + Arrays.toString(personArray));
    }
}

// Custom class implementing Comparable interface
class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        // Compare based on age
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
