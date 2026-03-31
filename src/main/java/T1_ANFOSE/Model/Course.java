package T1_ANFOSE.Model;

public class Course {

    private String id;
    private int credits;

    public Course(String id, int credits) {
        this.id = id;
        this.credits = credits;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public int getCredits() {
        return credits;
    }
}
