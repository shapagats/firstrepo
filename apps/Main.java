import java.io.*;

public class Main {
    public static void main(String[] args) {
        String fileName = "text1.txt";

        Employee employee = new Employee(101, "Saya", "Dias", 30, "IT");

        serializeObject(employee, fileName);
        System.out.println("Employee object serialized successfully.");

        Employee newEmployee = deserializeObject(fileName);
        if (newEmployee != null) {
            System.out.println("Employee object deserialized successfully:");
            System.out.println(newEmployee.toString());
        }
    }

    public static void serializeObject(Employee employee, String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(employee);
        } catch (IOException e) {
            System.err.println("Error occurred during serialization: " + e.getMessage());
        }
    }

    public static Employee deserializeObject(String fileName) {
        Employee employee = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            employee = (Employee) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error occurred during deserialization: " + e.getMessage());
        }
        return employee;
    }
}

class Employee implements Serializable {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String department;

    public Employee(int id, String name, String surname, int age, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }
}
