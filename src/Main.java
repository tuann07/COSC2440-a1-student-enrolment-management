import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = Menu.getInstance();

        Scanner sc = new Scanner(System.in);

        menu.doPopulateData(sc);

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
}
