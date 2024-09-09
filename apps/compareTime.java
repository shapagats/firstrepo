import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class compareTime {

    public static void main(String[] args) {
        final int num = 10000;

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < num; i++) {
            arrayList.add(i);
        }
        long arrayListAddTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < num; i++) {
            linkedList.add(i);
        }
        long linkedListAddTime = System.nanoTime() - startTime;

        System.out.println("Time taken to add " + num + " elements:");
        System.out.println("ArrayList: " + arrayListAddTime + " ns");
        System.out.println("LinkedList: " + linkedListAddTime + " ns");

        Random rand = new Random();
        int[] randomPositions = new int[num];
        for (int i = 0; i < num; i++) {
            randomPositions[i] = rand.nextInt(num);
        }

        startTime = System.nanoTime();
        for (int pos : randomPositions) {
            arrayList.add(pos, 0);
        }
        long arrayListAddRandomTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int pos : randomPositions) {
            linkedList.add(pos, 0);
        }
        long linkedListAddRandomTime = System.nanoTime() - startTime;

        System.out.println("\nTime taken to add elements to random positions:");
        System.out.println("ArrayList: " + arrayListAddRandomTime + " ns");
        System.out.println("LinkedList: " + linkedListAddRandomTime + " ns");

        startTime = System.nanoTime();
        for (int pos : randomPositions) {
            arrayList.remove(pos);
        }
        long arrayListRemoveTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int pos : randomPositions) {
            linkedList.remove(pos);
        }
        long linkedListRemoveTime = System.nanoTime() - startTime;

        System.out.println("\nTime taken to remove elements from random positions:");
        System.out.println("ArrayList: " + arrayListRemoveTime + " ns");
        System.out.println("LinkedList: " + linkedListRemoveTime + " ns");


        int searchElement = num / 2;
        startTime = System.nanoTime();
        arrayList.contains(searchElement);
        long arrayListSearchTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        linkedList.contains(searchElement);
        long linkedListSearchTime = System.nanoTime() - startTime;

        System.out.println("\nTime taken to search for an element:");
        System.out.println("ArrayList: " + arrayListSearchTime + " ns");
        System.out.println("LinkedList: " + linkedListSearchTime + " ns");

        startTime = System.nanoTime();
        for (Integer nums : arrayList) {
            
        }
        long arrayListIterationTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (Integer nums : linkedList) {
        }
        long linkedListIterationTime = System.nanoTime() - startTime;

        System.out.println("\nTime taken to iterate through the lists:");
        System.out.println("ArrayList: " + arrayListIterationTime + " ns");
        System.out.println("LinkedList: " + linkedListIterationTime + " ns");
    }
}
