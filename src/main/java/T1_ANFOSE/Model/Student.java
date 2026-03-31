package T1_ANFOSE.Model;

public class Student {

    private String id;
    private boolean isNew;

    public Student(String id, boolean isNew) {
        this.id = id;
        this.isNew = isNew;
    }

    public String getId() {
        return id;
    }

    public boolean isNew() {
        return isNew;
    }
}
