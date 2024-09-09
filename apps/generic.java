import java.util.Scanner;

public class generic {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter an object to push onto the stack:");
        String obj = s.nextLine();
        GenericStack<String> stack = new GenericStack<>(5);
        for (int i = 0; i < 5; i++) {
            stack.push(obj);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }
}
