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
        do {
            mainOption = menu.askMainMenu(sc);
            switch (mainOption) {
                case 1:
                    menu.doAddEnrolment(sc);
                    break;
                case 2:
                    menu.doUpdateEnrolment(sc);
                    break;
                case 5:
                    menu.doPrintAllEnrolments();
                    break;

            }

            secondaryOption = menu.askSecondaryMenu(sc);
            if (secondaryOption == 0) break;
        } while (mainOption != 0);

        sc.close();
    }

    public static void fileHandle(String fileName) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null)    // check for reaching the end of line
            {
                String[] data = line.split(splitBy);    // split a line by comma into an array of string

                Student studentTemp = new Student(data[0], data[1], data[2]);
                Course courseTemp = new Course(data[3], data[4], Integer.parseInt(data[5]));

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
