import java.util.*;

public class Produ {
    public static void main(String[] args) {
        Electronics phone = new Electronics(1, "Samsung", 98.0, "Technodom");
        Books book = new Books(2, "Calculus", 20.5, "Marwin");
        Clothing shirt = new Clothing(3, "Shirt", 15.8, "Cotton");

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(phone);
        cart.addItem(book);
        cart.addItem(shirt);

        System.out.println("Items in the shopping cart:");
        cart.displayItems();

        double totalPrice = cart.calculateTotalPrice();
        System.out.println("Total Price: $" + totalPrice);

    }
}

abstract class Product{
    protected int productId;
    protected String name;
    protected double price;
    protected String description;

    public Product(int productId, String name, double price, String description){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public abstract void displayDetails();
    public abstract double calculateShippingCost();

}

class Electronics extends Product{
    public Electronics(int productId, String name, double price, String description){
        super(productId, name, price, description);
    }

    @Override
    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Description: " + description);
    }

    @Override
    public double calculateShippingCost() {
        return 0.05 * price; 
    }
}

class Books extends Product{
    public Books(int productId, String name, double price, String description){
        super(productId, name, price, description);
    }

    @Override
    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Description: " + description);

    }

    @Override
    public double calculateShippingCost() {
        return 0.2*price; 
    }
}

class Clothing extends Product{
    public Clothing(int productId, String name, double price, String description){
        super(productId, name, price, description);
    }

    @Override
    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Description: " + description);

    }

    @Override
    public double calculateShippingCost() {
        return 0.1 * price; 
    }
}

class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public void removeItem(Product item) {
        items.remove(item);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product item : items) {
            totalPrice += item.price;
        }
        return totalPrice;
    }

    public void displayItems() {
        for (Product item : items) {
            item.displayDetails();
            System.out.println();
        }
    }
}