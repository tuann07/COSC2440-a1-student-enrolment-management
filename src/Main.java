import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("s1", "student", "8/28/2001");
        Student s2 = new Student("s2", "student", "8/28/2001");
        Course c1 = new Course("c1", "course", 1);
        Course c2 = new Course("c2", "course", 1);
        StudentEnrolmentSystem seSystem = new StudentEnrolmentSystem();
        seSystem.addStudent(s1);
        seSystem.addStudent(s2);
        seSystem.addCourse(c1);
        seSystem.addCourse(c2);

        // add
        seSystem.add("s1", "c1", "2022");
        seSystem.add("s1", "c1", "2022");
        seSystem.add("s1", "c1", "2022");
        seSystem.add("s1", "c1", "2022");

        seSystem.getAll();

        // update
        seSystem.update(1, "s2", "c2", "2002");

        // delete
        seSystem.delete(0);

        System.out.println("After");
        seSystem.getAll();
    }
}
