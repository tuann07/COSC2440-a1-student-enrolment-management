public class Course {
    private String id;
    private String name;
    private int numOfCredits;

    public Course() {
        this.id = "";
        this.name = "";
        this.numOfCredits = 0;
    }

    public Course(String id, String name, int numOfCredits) {
        this.id = id;
        this.name = name;
        this.numOfCredits = numOfCredits;
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

    public int getNumOfCredits() {
        return numOfCredits;
    }

    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    public String toCSV() {
        return id + "," + name + "," + numOfCredits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numOfCredits=" + numOfCredits +
                '}';
    }
}
