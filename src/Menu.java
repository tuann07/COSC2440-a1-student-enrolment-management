import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static Menu instance = new Menu();

    private Menu() {
    }

    public static Menu getInstance() {
        return instance;
    }

    private void printError(String message) {
        printSeparator();
        System.out.println("\tError: " + message);
    }

    private void printSuccess(String message) {
        printSeparator();
        System.out.println("\tSuccess: " + message);
    }

    private void printSeparator() {
        System.out.println("---");
    }

    public void doPopulateData(Scanner sc) {
        String temp, fileName;
        int option;

        System.out.println("Choose your data source");
        System.out.println("\t1. Use default file");
        System.out.println("\t2. Use custom file");

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

        if (option == 1) {
            fileName = "default.csv";
        } else {
            System.out.print("Please enter file name: ");
            fileName = sc.next();
        }

        FileHandling.fileHandle(fileName);
    }

    public int askMainMenu(Scanner sc) {
        String temp;
        int option;

        printSeparator();
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
        String temp;
        int option;

        printSeparator();
        System.out.println("Choose an option below to continue:");
        System.out.println("\t1. Show menu");
        System.out.println("\t0. Exit");

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

        printSeparator();
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

        printSeparator();
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

    public void doDeleteEnrolment(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String temp;
        int enrolmentId;

        printSeparator();
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Enrolment ID: ");
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
        if (!ses.delete(enrolmentId)) {
            printError("Enrolment not found in the database");
        }
        printSuccess("Enrolment deleted");
    }

    public void doPrintAnEnrolment(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String temp;
        int enrolmentId;

        printSeparator();
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Enrolment ID: ");
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
        System.out.println(ses.getOne(enrolmentId));
    }

    public void doPrintAllEnrolments() {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();

        printSeparator();
        ses.getAll().forEach(System.out::println);
    }

    public void doPrintCoursesInSemester(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String semester;

        System.out.println("---");
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Semester: ");
        semester = sc.next();

        ArrayList<Course> courses = ses.getCoursesInSemester(semester);

        if (courses.size() == 0) {
            printError("No record found in the database");
            return;
        }

        printSeparator();
        courses.forEach(System.out::println);
    }

    public void doPrintCoursesOfStudentInSemester(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String studentId, semester;

        System.out.println("---");
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Student ID: ");
        studentId = sc.next();
        System.out.print("Semester: ");
        semester = sc.next();

        ArrayList<Course> courses = ses.getCoursesOfStudentInSemester(studentId, semester);

        if (courses.size() == 0) {
            printError("No record found in the database");
            return;
        }

        printSeparator();
        courses.forEach(System.out::println);
    }

    public void doPrintStudentsOfCourseInSemester(Scanner sc) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String courseId, semester;

        System.out.println("---");
        System.out.println("Please provide the following information to complete the process");
        System.out.print("Course ID: ");
        courseId = sc.next();
        System.out.print("Semester: ");
        semester = sc.next();

        printSeparator();

        ArrayList<Student> students = ses.getStudentsOfCourseInSemester(courseId, semester);

        if (students.size() == 0) {
            printError("No record found in the database");
            return;
        }

        printSeparator();
        students.forEach(System.out::println);
    }
}
