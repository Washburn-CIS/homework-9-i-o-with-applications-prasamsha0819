import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class GradeBook {
    private static Student[] students;
    private static int studentCount = 0; 

    public static void main(String[] args)
        throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        // TODO: initialize students from contents of grades.txt file
        students = new Student[100];
        File gradesFile = new File("grades.txt");
        Scanner gradeScanner = new Scanner(gradesFile); // thiis will read from the file

        while (gradeScanner.hasNextLine()){
            String line = gradeScanner.nextLine();
            String[] tokens = line.split(",");
            if (tokens.length == 3){
            String lastName = tokens[0];
            String firstName = tokens[1];
            double grade = Double.parseDouble(tokens[2]); 
            students[studentCount++] = new Student(firstName, lastName, grade); 

            }
        }
        
        gradeScanner.close(); 

        System.out.println("Welcome to the CM111 Grade Book App!");

        while(true) {
            System.out.println("\nPlease make a selection:\n");
            System.out.println("1) List Class Grades");
            System.out.println("2) Update Grade");
            System.out.println("3) Exit");
            System.out.print("\nPlease choose an option: ");
            String choice = input.nextLine();
            System.out.println();
            switch(choice) {
                case "1": 
                    for(Student student: students) {
                        System.out.printf("%s, %s: %f%n", student.getLastName(), 
                                                        student.getFirstName(), 
                                                        student.getGrade());
                    }
                    break;
                case "2":
                    System.out.println("Enter First Name: ");
                    String fname = input.nextLine();
                    System.out.println("Enter Last Name: ");
                    String lname = input.nextLine();
                    
                    for(Student student: students) {
                        if(student.getFirstName().equals(fname) &&
                           student.getLastName().equals(lname)) {
                           System.out.println("Enter Grade: ");
                           student.setGrade(Double.parseDouble(input.nextLine()));
                           System.out.println("Grade updated");
                           break;
                        }
                    }
                    System.out.println("Student not found");
                    break;
                case "3":
                    // Challenge: write code to save the grades to grades.txt
                    PrintWriter writer = new PrintWriter("grades.txt");
                    for (Student student : students) {
                        writer.printf("%s,%s,%.2f%n", 
                            student.getLastName(), 
                            student.getFirstName(), 
                            student.getGrade()); 
                    }
                    writer.close(); 
                    System.out.println("Grades saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }
    }
}
