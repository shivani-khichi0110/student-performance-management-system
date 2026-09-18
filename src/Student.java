public class Student {
    private String id;
    private String name;
    private String course;
    private int semester;

    public Student(String id, String name, String course, int semester) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.semester = semester;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String toFileString() {
        return id + "|" + name + "|" + course + "|" + semester;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split("\\|");

        if (parts.length != 4) {
            return null;
        }

        try {
            int semester = Integer.parseInt(parts[3]);
            return new Student(parts[0], parts[1], parts[2], semester);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %-10s | Name: %-20s | Course: %-15s | Semester: %d",
            id, name, course, semester
        );
    }
}