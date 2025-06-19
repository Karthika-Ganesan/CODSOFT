import java.util.*;
import java.io.*;

// 1. Student class
class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private String email;

    public Student(String name, int rollNumber, String grade, String email) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.email = email;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + ", Email: " + email;
    }

    public String toFileString() {
        return name + "," + rollNumber + "," + grade + "," + email;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
    }
}

// 2. Student Management System
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentManagementSystem() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    public boolean removeStudent(int rollNumber) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getRollNumber() == rollNumber) {
                iterator.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException e) {
            // File not found – skip loading
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
}

// 3. Main Application with Console UI
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int roll = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    if (name.isEmpty() || grade.isEmpty() || email.isEmpty()) {
                        System.out.println("All fields are required.");
                    } else {
                        sms.addStudent(new Student(name, roll, grade, email));
                        System.out.println("Student added successfully.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    int removeRoll = scanner.nextInt();
                    if (sms.removeStudent(removeRoll)) {
                        System.out.println("Student removed.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    int searchRoll = scanner.nextInt();
                    Student found = sms.searchStudent(searchRoll);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    List<Student> all = sms.getAllStudents();
                    if (all.isEmpty()) {
                        System.out.println("No students available.");
                    } else {
                        for (Student s : all) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Roll Number to edit: ");
                    int editRoll = scanner.nextInt();
                    scanner.nextLine();
                    Student toEdit = sms.searchStudent(editRoll);
                    if (toEdit != null) {
                        System.out.print("Enter new Grade: ");
                        String newGrade = scanner.nextLine();
                        System.out.print("Enter new Email: ");
                        String newEmail = scanner.nextLine();
                        toEdit.setGrade(newGrade);
                        toEdit.setEmail(newEmail);
                        sms.saveToFile();
                        System.out.println("Student updated.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
