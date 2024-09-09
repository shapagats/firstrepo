import java.util.*;

public class linked<E> {
    private E[] arr;
    @SuppressWarnings("unchecked")
    public linked(int a){
        size = a;
        arr = (E[]) new Object[size];
    }
    private int counter = 0;
    public boolean check(){
        if (counter+1==size){
            System.out.println("Stop");
            return true;
        }
        return false;
    }
    private E Node;
    private int size;
    private void addFirst(E element){
        if (check()==false) {
            E next = arr[0];
            arr[0] = element;
            for (int i = 1; i < counter+2; i++) {
                arr[i] = next;
                next = arr[i + 1];
            }
            counter++;
        }
    }
    private void add(E element){
        if (check()==false) {

            
            if (size> counter) {
                arr[counter] = element;
            }
            counter++;
        }
    }
    private void add(int a, E element){
        if (check()==false) {

            if (arr[a - 1] == null || a > size || a > counter + 1) {
                System.out.println("dsad");
            }
            arr[counter] = element;
        }
    }
    private void remove(int a) {
        if (a <= counter) {
            arr[a] = null;
            for (int i = counter-a; i < counter-1; i++){
                arr[i] = arr[i+1];
            }
            arr[counter-1] = null;
            counter--;
        }
        System.out.println("dada");
    }
}