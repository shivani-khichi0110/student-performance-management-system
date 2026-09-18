import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private final Path dataDirectory;
    private final Path studentFile;
    private final Path performanceFile;

    public FileManager() {
        dataDirectory = Paths.get("data");
        studentFile = dataDirectory.resolve("students.txt");
        performanceFile = dataDirectory.resolve("performance.txt");

        createDataDirectory();
    }

    private void createDataDirectory() {
        try {
            Files.createDirectories(dataDirectory);

            if (!Files.exists(studentFile)) {
                Files.createFile(studentFile);
            }

            if (!Files.exists(performanceFile)) {
                Files.createFile(performanceFile);
            }

        } catch (IOException e) {
            System.out.println("Error creating data files: " + e.getMessage());
        }
    }

    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(studentFile);

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    Student student = Student.fromFileString(line);

                    if (student != null) {
                        students.add(student);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    public void saveStudents(List<Student> students) {
        List<String> lines = new ArrayList<>();

        for (Student student : students) {
            lines.add(student.toFileString());
        }

        try {
            Files.write(studentFile, lines);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public List<Performance> loadPerformances() {
        List<Performance> performances = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(performanceFile);

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    Performance performance =
                            Performance.fromFileString(line);

                    if (performance != null) {
                        performances.add(performance);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading performance data: "
                    + e.getMessage());
        }

        return performances;
    }

    public void savePerformances(List<Performance> performances) {
        List<String> lines = new ArrayList<>();

        for (Performance performance : performances) {
            lines.add(performance.toFileString());
        }

        try {
            Files.write(performanceFile, lines);
        } catch (IOException e) {
            System.out.println("Error saving performance data: "
                    + e.getMessage());
        }
    }
}