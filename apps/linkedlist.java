class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

public class linkedlist {
    private Node head;

    public linkedlist() {
        this.head = null;
    }


    public void insertFront(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    public void insertEnd(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
    }

    public void insertAfter(Node prevNode, int value) {
        if (prevNode == null) {
            System.out.println("Previous node cannot be null");
            return;
        }
        Node newNode = new Node(value);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }

    public void delete(int key) {
        Node temp = head;
        Node prev = null;

        if (temp != null && temp.value == key) {
            head = temp.next;
            return;
        }

        while (temp != null && temp.value != key) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) return;

        prev.next = temp.next;
    }

    
    public boolean isEmpty() {
        return head == null;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    
    public static void main(String[] args) {
        linkedlist list = new linkedlist();

        
        list.insertFront(3);
        list.insertFront(2);
        list.insertFront(1);
        
        list.display();

      
        list.insertEnd(4);
        list.insertEnd(5);
        list.display();

        list.insertAfter(list.head.next, 8);
        list.display();


        list.delete(3); 
        list.display();


        System.out.println("List empty? " + list.isEmpty());


        list.delete(1);
        list.delete(2);
        list.delete(4);
        list.delete(5);
        System.out.println("List empty? " + list.isEmpty());
    }
}
