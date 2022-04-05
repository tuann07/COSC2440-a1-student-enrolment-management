import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu menu = Menu.getInstance();

        Scanner sc = new Scanner(System.in);
        String fileName = menu.askFileName(sc);

        fileHandle(fileName);

        int mainOption, secondaryOption;
        while (true) {
            mainOption = menu.askMainMenu(sc);
            switch (mainOption) {
                case 1 -> menu.doAddEnrolment(sc);
                case 2 -> menu.doUpdateEnrolment(sc);
                case 3 -> menu.doDeleteEnrolment(sc);
                case 4 -> menu.doPrintAnEnrolment(sc);
                case 5 -> menu.doPrintAllEnrolments();
                case 6 -> menu.doPrintCoursesInSemester(sc);
                case 7 -> menu.doPrintCoursesOfStudentInSemester(sc);
                case 8 -> menu.doPrintStudentsOfCourseInSemester(sc);
            }

            if (mainOption == 0) break;
            secondaryOption = menu.askSecondaryMenu(sc);
            if (secondaryOption == 0) break;
        }

        sc.close();
    }

    public static void fileHandle(String fileName) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        int count = 0;
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)    // check for reaching the end of line
            {
                count++;
                String[] data = line.split(splitBy);    // split a line by comma into an array of string

                Student studentTemp;
                Course courseTemp;
                try {
                    studentTemp = new Student(data[0], data[1], data[2]);
                    courseTemp = new Course(data[3], data[4], Integer.parseInt(data[5]));
                } catch (Exception e) {
                    System.out.println("\tError: Cannot add enrolment at line " + count);
                    continue;
                }

                ses.addStudent(studentTemp);
                ses.addCourse(courseTemp);
                ses.add(studentTemp.getId(), courseTemp.getId(), data[6]);
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }
}
