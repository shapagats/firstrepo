import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


interface Product {
    int getId();
    String getName();
    double getPrice();
    void display();
}


class InventoryItem<T> implements Product {
    private int id;
    private String name;
    private double price;

    public InventoryItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price);
    }
}


class Inventory<T extends Product> {
    private List<T> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        if (!containsId(item.getId())) {
            items.add(item);
            System.out.println("Product added: " + item.getName());
        } else {
            System.out.println("Product with ID " + item.getId() + " already exists.");
        }
    }

    
    public void remove(int id) {
        for (T item : items) {
            if (item.getId() == id) {
                items.remove(item);
                System.out.println("Product removed: " + item.getName());
                return;
            }
        }
        System.out.println("Product with ID " + id + " not found.");
    }

   
    public void display() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (T item : items) {
                item.display();
            }
        }
    }

    
    public void search(int id) {
        for (T item : items) {
            if (item.getId() == id) {
                System.out.println("Product found: " + item.getName());
                return;
            }
        }
        System.out.println("Product with ID " + id + " not found.");
    }

    
    private boolean containsId(int id) {
        for (T item : items) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }
}


public class invent {
    public static void main(String[] args) {
        Inventory<InventoryItem> inventory = new Inventory<>();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nInventory");
            System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("3. Display");
            System.out.println("4. Search");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    inventory.add(new InventoryItem(id, name, price));
                    break;
                case 2:
                    System.out.print("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    inventory.remove(removeId);
                    break;
                case 3:
                    inventory.display();
                    break;
                case 4:
                    System.out.print("Enter product ID to search: ");
                    int searchId = scanner.nextInt();
                    inventory.search(searchId);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);

        scanner.close();
    }
}


