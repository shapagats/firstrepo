import java.util.ArrayList;
import java.util.List;

public class student {
    private int id;
    private String name;
    private String surname;
    

    public static void main(String[] args) {

        List<student> data = new ArrayList<>();

    }

    public student() {

    }

    public student(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
     
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


   public void addStud(){
     
     System.out.println("enter id:");

   }
}
