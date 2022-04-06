import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class StudentEnrolmentSystemTest {

    public static StudentEnrolmentSystem ses = StudentEnrolmentSystem.getInstance();

    @BeforeClass
    public static void setUpBeforeClass() {
        // test using the sample default.csv file
        FileHandling.fileHandle("default.csv");
        System.out.println("File csv with 15 items loaded");
    }

    @Test
    public void testAdd() {
        System.out.println("Test Add");
        ses.add("S101312", "COSC3321", "TestAdd");
        boolean isExist = false;
        for (StudentEnrolment se : ses.getAll()) {
            if (se.getSemester().equals("TestAdd")) {
                isExist = true;
                break;
            }
        }
        assertTrue(isExist);
    }

    @Test
    public void testUpdate() {
        System.out.println("Test Update");
        ses.update(1, "S101312", "COSC4030", "TestUpdate");
        boolean isExist = false;
        for (StudentEnrolment se : ses.getAll()) {
            if (se.getSemester().equals("TestUpdate")) {
                isExist = true;
                break;
            }
        }
        assertTrue(isExist);
    }

    @Test
    public void testDelete() {
        System.out.println("Test Delete");
        ses.delete(10);
        assertEquals(15, ses.getAll().size());
    }

    @Test
    public void testGetOne() {
        System.out.println("Test Get One");

        StudentEnrolment se = ses.getOne(0);
        boolean isRight = se.getStudent().getId().equals("S101312") && se.getCourse().getId().equals("COSC4030") && se.getSemester().equals("2020C");

        assertTrue(isRight);
    }

    @Test
    public void testGetAll() {
        System.out.println("Test Get All");
        assertEquals(15, ses.getAll().size());
    }
}