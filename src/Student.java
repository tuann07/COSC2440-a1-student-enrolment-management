import java.time.LocalDate;

public class Student {
    private String id;
    private String name;
    private String birthdate;

    public Student() {
        this.id = "";
        this.name = "";
        this.birthdate = "";
    }

    public Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String toCSV() {
        return id + "," + name + "," + birthdate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
