import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandling {
    public static void fileHandle(String fileName) {
        StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();
        int count = 0;
        String line;
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
