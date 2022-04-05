public class StudentEnrolment {
    private int id;
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment() {
        this.student = null;
        this.course = null;
        this.semester = "";
    }

    public StudentEnrolment(Student student, Course course, String semester, int id) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "StudentEnrolment{" +
                "id=" + id +
                ", student=" + student.getId() +
                ", course=" + course.getId() +
                ", semester='" + semester + '\'' +
                '}';
    }
}
