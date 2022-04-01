import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Menu instance = new Menu();

    private Menu() {
    }

    public static Menu getInstance() {
        return instance;
    }

    private void printError(String message) {
        System.out.println("\tError: " + message);
    }

    private void printSuccess(String message) {
        System.out.println("\tSuccess: " + message);
    }

    public String askFileName(Scanner sc) {
        System.out.println("Choose your data source");
        System.out.println("\t1. Use default file");
        System.out.println("\t2. Use custom file");
        String temp;
        int option;
        while (true) {
            try {
                System.out.print("Your option: ");
                temp = sc.next();
                option = Integer.parseInt(temp);
                if (option == 1 || option == 2) break;
                throw new Exception();
            } catch (Exception e) {
                printError("Please enter a valid option");
            }
        }
        String fileName;
        if (option == 1) {
            fileName = "default.csv";
        } else {
            System.out.print("Please enter file name: ");
            fileName = sc.next();
        }
        return fileName;
    }

    public int askMainMenu(Scanner sc) {
        System.out.println("---");
        System.out.println("Choose an option below to continue:");
        System.out.println("\t1. Add an enrolment");
        System.out.println("\t2. Update an enrolment");
        System.out.println("\t3. Delete an enrolment");
        System.out.println("\t4. Print an enrolment");
        System.out.println("\t5. Print all enrolments");
        System.out.println("\t6. Print all courses in a semester");
        System.out.println("\t7. Print all courses of a student in a semester");
        System.out.println("\t8. Print all students of a course in a semester");
        System.out.println("\t0. Exit");
        String temp;
        int option;
        while (true) {
            try {
                System.out.print("Your option: ");
                temp = sc.next();
                option = Integer.parseInt(temp);
                if (option >= 0 && option <= 8) break;
                throw new Exception();
            } catch (Exception e) {
                printError("Please enter a valid option");
            }
        }
        return option;
    }

    public int askSecondaryMenu(Scanner sc) {
        System.out.println("---");
        System.out.println("Choose an option below to continue:");
        System.out.println("\t1. Show menu again");
        System.out.println("\t0. Exit");
        String temp;
        int option;
        while (true) {
            try {
                System.out.print("Your option: ");
                temp = sc.next();
                option = Integer.parseInt(temp);
                if (option == 0 || option == 1) break;
                throw new Exception();
            } catch (Exception e) {
                printError("Please enter a valid option");
            }
        }
        return option;
    }

    public void doAddEnrolment(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String studentId, courseId, semester;
        System.out.println("---");
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Student ID: ");
        studentId = sc.next();
        System.out.print("Course ID: ");
        courseId = sc.next();
        System.out.print("Semester: ");
        semester = sc.next();
        if (!ses.add(studentId, courseId, semester)) {
            printError("Student or Course not found in the database");
            return;
        }
        printSuccess("Enrolment added");
    }

    public void doUpdateEnrolment(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String studentId, courseId, semester, temp;
        int enrolmentId;
        System.out.println("---");
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Enrolment ID to modify: ");
        try {
            temp = sc.next();
            enrolmentId = Integer.parseInt(temp);
            if (ses.getOne(enrolmentId) == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            printError("Enrolment not found in the database");
            return;
        }
        System.out.print("Student ID: ");
        studentId = sc.next();
        System.out.print("Course ID: ");
        courseId = sc.next();
        System.out.print("Semester: ");
        semester = sc.next();
        if (!ses.update(enrolmentId, studentId, courseId, semester)) {
            printError("Student or Course not found in the database");
            return;
        }
        printSuccess("Enrolment updated");
    }

    public void doPrintAllEnrolments() {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        ses.getAll().forEach(System.out::println);
    }
}
