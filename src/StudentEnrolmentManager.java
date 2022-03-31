import java.util.ArrayList;

public interface StudentEnrolmentManager {
    public boolean add(String studentId, String courseId, String semester);
    public boolean update(int enrolmentId, String studentId, String courseId, String semester);
    public boolean delete(int enrolmentId);
    public StudentEnrolment getOne(int enrolmentId);
    public ArrayList<StudentEnrolment> getAll();
}
