import java.util.Iterator;
import java.util.LinkedList;

public class Stack<E> {
    private LinkedList<E> list;

    public Stack() {
        list = new LinkedList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public void push(E e) {
        list.addFirst(e);
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeFirst();
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (E item : list) {
            sb.append(item).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= 6; i++) {
            stack.push(i);
            System.out.println(i + "_After push(" + i + "): " + stack);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.size() + "_linkedStack.peek(): " + stack.peek());
            System.out.println(stack.size() + "_linkedStack.pop(): " + stack.pop());
            System.out.println(stack.size() + "_After pop: " + stack);
        }
      
    }
}

