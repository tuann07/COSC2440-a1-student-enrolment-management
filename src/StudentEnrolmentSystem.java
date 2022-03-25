import java.util.ArrayList;

public class StudentEnrolmentSystem implements StudentEnrolmentManager {
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<StudentEnrolment> enrolmentList;
    static private int enrolmentCounter = 0;

    public StudentEnrolmentSystem() {
        studentList = new ArrayList<Student>();
        courseList = new ArrayList<Course>();
        enrolmentList = new ArrayList<StudentEnrolment>();
    }

    public Student getStudent(String studentId) {
        for (Student s : studentList) {
            if (s.getId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    public Course getCourse(String courseId) {
        for (Course c : courseList) {
            if (c.getId().equals(courseId)) {
                return c;
            }
        }
        return null;
    }

    public void addStudent(Student s) {
        if (getStudent(s.getId()) != null) {
            return;
        }
        studentList.add(s);
    }

    public void addCourse(Course c) {
        if (getCourse(c.getId()) != null) {
            return;
        }
        courseList.add(c);
    }

    @Override
    public boolean add(String studentId, String courseId, String semester) {
        Student student = getStudent(studentId);
        if (student == null) {
            return false;
        }
        Course course = getCourse(courseId);
        if (course == null) {
            return false;
        }

        enrolmentList.add(new StudentEnrolment(student, course, semester, enrolmentCounter));
        enrolmentCounter++;
        return true;
    }

    @Override
    public boolean update(int enrolmentId, String studentId, String courseId, String semester) {
        StudentEnrolment se = getOne(enrolmentId);
        if (se == null) {
            return false;
        }
        Student student = getStudent(studentId);
        if (student == null) {
            return false;
        }
        Course course = getCourse(courseId);
        if (course == null) {
            return false;
        }
        se.setStudent(student);
        se.setCourse(course);
        se.setSemester(semester);
        return true;
    }

    @Override
    public boolean delete(int enrolmentId) {
        StudentEnrolment se = getOne(enrolmentId);
        if (se == null) {
            return false;
        }
        enrolmentList.remove(se);
        return true;
    }

    @Override
    public StudentEnrolment getOne(int enrolmentId) {
        for (StudentEnrolment se : enrolmentList) {
            if (se.getId() == enrolmentId) {
                return se;
            }
        }
        return null;
    }

    @Override
    public void getAll() {
        for (StudentEnrolment se : enrolmentList) {
            System.out.println(se);
        }
    }
}
