import java.util.ArrayList;

public class StudentEnrolmentSystem implements StudentEnrolmentManager {
    private static StudentEnrolmentSystem instance = new StudentEnrolmentSystem();

    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<StudentEnrolment> enrolmentList;
    static private int enrolmentCounter = 0;

    private StudentEnrolmentSystem() {
        studentList = new ArrayList<Student>();
        courseList = new ArrayList<Course>();
        enrolmentList = new ArrayList<StudentEnrolment>();
    }

    public static StudentEnrolmentSystem getInstance() {
        return instance;
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

    // get all courses in 1 semester
    public ArrayList<Course> getCoursesInSemester(String semester) {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (StudentEnrolment se : enrolmentList) {
            if (se.getSemester().equals(semester)) {
                courses.add(se.getCourse());
            }
        }
        return courses;
    }

    // get all courses of 1 student in 1 semester
    public ArrayList<Course> getStudentCoursesInSemester(String studentId, String semester) {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (StudentEnrolment se : enrolmentList) {
            if (se.getSemester().equals(semester) && se.getStudent().getId().equals(studentId)) {
                courses.add(se.getCourse());
            }
        }
        return courses;
    }

    // get all student of 1 course in 1 semester
    public ArrayList<Student> getCourseStudentsInSemester(String courseId, String semester) {
        ArrayList<Student> students = new ArrayList<Student>();
        for (StudentEnrolment se : enrolmentList) {
            if (se.getSemester().equals(semester) && se.getCourse().getId().equals(courseId)) {
                students.add(se.getStudent());
            }
        }
        return students;
    }

    // interface
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
    public ArrayList<StudentEnrolment> getAll() {
        return enrolmentList;
    }
}
