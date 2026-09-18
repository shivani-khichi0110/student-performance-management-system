import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static InputHelper inputHelper =
            new InputHelper(scanner);

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();

        StudentManager studentManager =
                new StudentManager(fileManager);

        PerformanceManager performanceManager =
                new PerformanceManager(fileManager);

        ReportManager reportManager =
                new ReportManager(
                        studentManager,
                        performanceManager
                );

        boolean running = true;

        System.out.println("\n==============================================");
        System.out.println("   STUDENT PERFORMANCE MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        while (running) {

            displayMenu();

            int choice = inputHelper.readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent(studentManager);
                    break;

                case 2:
                    viewStudents(studentManager);
                    break;

                case 3:
                    searchStudent(studentManager);
                    break;

                case 4:
                    updateStudent(studentManager);
                    break;

                case 5:
                    deleteStudent(
                            studentManager,
                            performanceManager
                    );
                    break;

                case 6:
                    addPerformance(
                            studentManager,
                            performanceManager
                    );
                    break;

                case 7:
                    viewPerformance(
                            studentManager,
                            reportManager
                    );
                    break;

                case 8:
                    reportManager.generateOverallReport();
                    break;

                case 9:
                    running = false;
                    System.out.println(
                            "\nThank you for using the system!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select 1-9."
                    );
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n----------------------------------------------");
        System.out.println("                  MAIN MENU");
        System.out.println("----------------------------------------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Add Performance");
        System.out.println("7. View Student Performance");
        System.out.println("8. Generate Overall Report");
        System.out.println("9. Exit");
        System.out.println("----------------------------------------------");
    }

    private static void addStudent(
            StudentManager studentManager) {

        System.out.println("\n--- ADD STUDENT ---");

        String id;

        while (true) {
            id = inputHelper.readRequiredString(
                    "Enter Student ID: "
            );

            if (!ValidationUtil.isValidStudentId(id)) {
                System.out.println(
                        "Invalid ID. Use letters, numbers, _ or -."
                );
                continue;
            }

            if (studentManager.findStudentById(id) != null) {
                System.out.println(
                        "Student ID already exists."
                );
                continue;
            }

            break;
        }

        String name;

        while (true) {
            name = inputHelper.readRequiredString(
                    "Enter Student Name: "
            );

            if (ValidationUtil.isValidName(name)) {
                break;
            }

            System.out.println(
                    "Invalid name. Use alphabetic characters only."
            );
        }

        String course;

        while (true) {
            course = inputHelper.readRequiredString(
                    "Enter Course: "
            );

            if (ValidationUtil.isValidCourse(course)) {
                break;
            }

            System.out.println("Invalid course.");
        }

        int semester;

        while (true) {
            semester = inputHelper.readInt(
                    "Enter Semester (1-12): "
            );

            if (ValidationUtil.isValidSemester(semester)) {
                break;
            }

            System.out.println(
                    "Semester must be between 1 and 12."
            );
        }

        Student student =
                new Student(id, name, course, semester);

        if (studentManager.addStudent(student)) {
            System.out.println(
                    "Student added successfully!"
            );
        }
    }

    private static void viewStudents(
            StudentManager studentManager) {

        System.out.println("\n--- ALL STUDENTS ---");

        List<Student> students =
                studentManager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent(
            StudentManager studentManager) {

        System.out.println("\n--- SEARCH STUDENT ---");

        String id = inputHelper.readRequiredString(
                "Enter Student ID: "
        );

        Student student =
                studentManager.findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("\nStudent Found:");
            System.out.println(student);
        }
    }

    private static void updateStudent(
            StudentManager studentManager) {

        System.out.println("\n--- UPDATE STUDENT ---");

        String id = inputHelper.readRequiredString(
                "Enter Student ID: "
        );

        Student existing =
                studentManager.findStudentById(id);

        if (existing == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current Details:");
        System.out.println(existing);

        String name = inputHelper.readRequiredString(
                "Enter New Name: "
        );

        String course = inputHelper.readRequiredString(
                "Enter New Course: "
        );

        int semester;

        while (true) {
            semester = inputHelper.readInt(
                    "Enter New Semester (1-12): "
            );

            if (ValidationUtil.isValidSemester(semester)) {
                break;
            }

            System.out.println(
                    "Semester must be between 1 and 12."
            );
        }

        boolean updated =
                studentManager.updateStudent(
                        id,
                        name,
                        course,
                        semester
                );

        if (updated) {
            System.out.println(
                    "Student updated successfully!"
            );
        }
    }

    private static void deleteStudent(
            StudentManager studentManager,
            PerformanceManager performanceManager) {

        System.out.println("\n--- DELETE STUDENT ---");

        String id = inputHelper.readRequiredString(
                "Enter Student ID: "
        );

        Student student =
                studentManager.findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println(student);

        String confirmation = inputHelper.readString(
                "Are you sure? Enter Y to delete: "
        );

        if (confirmation.equalsIgnoreCase("Y")) {

            boolean deleted =
                    studentManager.deleteStudent(id);

            if (deleted) {
                performanceManager.deleteByStudentId(id);

                System.out.println(
                        "Student deleted successfully!"
                );
            }

        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    private static void addPerformance(
            StudentManager studentManager,
            PerformanceManager performanceManager) {

        System.out.println("\n--- ADD PERFORMANCE ---");

        String studentId = inputHelper.readRequiredString(
                "Enter Student ID: "
        );

        Student student =
                studentManager.findStudentById(studentId);

        if (student == null) {
            System.out.println(
                    "Student does not exist. Add student first."
            );
            return;
        }

        String subject;

        while (true) {
            subject = inputHelper.readRequiredString(
                    "Enter Subject: "
            );

            if (ValidationUtil.isValidSubject(subject)) {
                break;
            }

            System.out.println("Invalid subject.");
        }

        double marks;

        while (true) {

            marks = inputHelper.readDouble(
                    "Enter Marks (0-100): "
            );

            if (ValidationUtil.isValidMarks(marks)) {
                break;
            }

            System.out.println(
                    "Marks must be between 0 and 100."
            );
        }

        Performance performance =
                new Performance(
                        studentId,
                        subject,
                        marks
                );

        performanceManager.addPerformance(performance);

        System.out.println(
                "Performance added successfully!"
        );

        System.out.println(
                "Grade: " + performance.getGrade()
        );
    }

    private static void viewPerformance(
            StudentManager studentManager,
            ReportManager reportManager) {

        System.out.println("\n--- VIEW STUDENT PERFORMANCE ---");

        String studentId =
                inputHelper.readRequiredString(
                        "Enter Student ID: "
                );

        if (studentManager.findStudentById(studentId)
                == null) {

            System.out.println("Student not found.");
            return;
        }

        reportManager.generateStudentReport(studentId);
    }
}