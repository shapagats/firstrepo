import java.util.Arrays;
import java.util.Scanner;

public class Stud {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Student[] students = { 
            new Student("1", "Asel", 3),
            new Student("2", "Bakyt", 8),                
            new Student("3", "Nurdos", 21),
            new Student("4", "Nurlan", 11),
            new Student("5", "Nur", 20)

        };

        System.out.println("Students:");
        printStudents(students);
        System.out.println();        

        sortStudents(students);

        System.out.println("Sorted Students based on age:");
        printStudents(students);
    }

    public static void sortStudents(Student[] students) {
        int n = students.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].getAge() > students[j + 1].getAge()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}


class Student implements Comparable<Student> {
    private String studentId;
    private String name;
    private int age;

    public Student(String studentId, String name, int age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Student otherStud) {
        return Integer.compare(this.age, otherStud.age);
    }

    @Override
    public String toString() {
        return "Student{" +"studentId=" + studentId + ", name=" + name + ", age=" + age +"}";
    }
}


