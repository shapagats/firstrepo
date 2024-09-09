
interface Printable {
    void display();
}


class Box<T> {
    private T item;

   
    public Box(T item) {
        this.item = item;
    }

 
    public T getItem() {
        return item;
    }
}


class Book implements Printable {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public void display() {
        System.out.println("Book Title: " + title);
    }
}

class Fruit implements Printable {
    private String name;


    public Fruit(String name) {
        this.name = name;
    }


    @Override
    public void display() {
        System.out.println("Fruit Name: " + name);
    }
}


public class Boxcon {


    public static <T extends Printable> void printBoxContents(Box<T> box) {
        T item = box.getItem();
        if (item != null) {
            item.display();
        } else {
            System.out.println("Box is empty");
        }
    }

    public static void main(String[] args) {

        Box<Book> bookBox = new Box<>(new Book("Java Programming"));
        Box<Fruit> fruitBox = new Box<>(new Fruit("Apple"));
        Box<Book> emptyBox = new Box<>(null);

      
        System.out.println("Contents of Book Box:");
        printBoxContents(bookBox);

        System.out.println("\nContents of Fruit Box:");
        printBoxContents(fruitBox);

        System.out.println("\nContents of Empty Box:");
        printBoxContents(emptyBox);
    }
}
