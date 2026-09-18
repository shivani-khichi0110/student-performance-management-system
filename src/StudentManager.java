import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final List<Student> students;
    private final FileManager fileManager;

    public StudentManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.students = new ArrayList<>(fileManager.loadStudents());
    }

    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()) != null) {
            return false;
        }

        students.add(student);
        fileManager.saveStudents(students);
        return true;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    public boolean updateStudent(
            String id,
            String name,
            String course,
            int semester) {

        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setCourse(course);
        student.setSemester(semester);

        fileManager.saveStudents(students);
        return true;
    }

    public boolean deleteStudent(String id) {
        Student student = findStudentById(id);

        if (student == null) {
            return false;
        }

        students.remove(student);
        fileManager.saveStudents(students);
        return true;
    }

    public int getStudentCount() {
        return students.size();
    }
}