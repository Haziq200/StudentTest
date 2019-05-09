package sg.edu.rp.dmsd.studenttest;

public class Student {
    private int id;
    private  String name;
    private String gpa;

    public Student(int id, String name, String gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGpa() {
        return gpa;
    }
}
