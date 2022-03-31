import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        StudentEnrolmentSystem ses = new StudentEnrolmentSystem();

        fileHandle("default.csv", ses);

        ses.getAll().forEach(System.out::println);
    }

    public static void fileHandle(String fileName, StudentEnrolmentSystem ses) {
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
            e.printStackTrace();
        }
    }
}
