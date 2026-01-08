import java.io.*;
import java.util.*;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;
    private int age;

    public Student(String name, int rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Roll No: " + rollNumber +
               ", Name: " + name +
               ", Grade: " + grade +
               ", Age: " + age;
    }
}

public class StudentManagementSystem {
    private static final String FILE_NAME = "students.dat";
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();
        int choice;

        do {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    saveToFile();
                    System.out.println("Exiting application");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void addStudent() {
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty");
            return;
        }

        System.out.print("Enter roll number: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid roll number");
            scanner.next();
            return;
        }
        int roll = scanner.nextInt();

        System.out.print("Enter age: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid age");
            scanner.next();
            return;
        }
        int age = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty");
            return;
        }

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("Roll number already exists");
                return;
            }
        }

        students.add(new Student(name, roll, grade, age));
        System.out.println("Student added successfully");
    }

    private static void editStudent() {
        System.out.print("Enter roll number to edit: ");
        int roll = scanner.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                scanner.nextLine();

                System.out.print("Enter new name: ");
                s.setName(scanner.nextLine());

                System.out.print("Enter new age: ");
                s.setAge(scanner.nextInt());

                scanner.nextLine();
                System.out.print("Enter new grade: ");
                s.setGrade(scanner.nextLine());

                System.out.println("Student updated");
                return;
            }
        }
        System.out.println("Student not found");
    }

    private static void removeStudent() {
        System.out.print("Enter roll number to remove: ");
        int roll = scanner.nextInt();

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getRollNumber() == roll) {
                iterator.remove();
                System.out.println("Student removed");
                return;
            }
        }
        System.out.println("Student not found");
    }

    private static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        int roll = scanner.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    private static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}