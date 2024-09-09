import java.util.*;

public class iterator {
    public static void main(String[] args) {
        Collection<Integer> list = new ArrayList<>();
        list.add(108);
        list.add(23);
        list.add(12);
        list.add(99);
        list.add(67);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next(); 
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}
