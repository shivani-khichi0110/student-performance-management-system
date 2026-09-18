import java.util.List;

public class ReportManager {

    private final StudentManager studentManager;
    private final PerformanceManager performanceManager;

    public ReportManager(
            StudentManager studentManager,
            PerformanceManager performanceManager) {

        this.studentManager = studentManager;
        this.performanceManager = performanceManager;
    }

    public void generateStudentReport(String studentId) {

        Student student = studentManager.findStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Performance> performances =
                performanceManager.getPerformancesByStudent(studentId);

        System.out.println("\n========================================");
        System.out.println("           STUDENT REPORT");
        System.out.println("========================================");

        System.out.println("Student ID : " + student.getId());
        System.out.println("Name       : " + student.getName());
        System.out.println("Course     : " + student.getCourse());
        System.out.println("Semester   : " + student.getSemester());

        System.out.println("----------------------------------------");

        if (performances.isEmpty()) {
            System.out.println("No performance records found.");
            return;
        }

        for (Performance performance : performances) {
            System.out.println(performance);
        }

        double total =
                performanceManager.calculateTotal(studentId);

        double average =
                performanceManager.calculateAverage(studentId);

        System.out.println("----------------------------------------");
        System.out.printf("Total Marks : %.2f%n", total);
        System.out.printf("Average     : %.2f%n", average);
        System.out.println("Overall Grade: " + calculateGrade(average));
        System.out.println("========================================");
    }

    private String calculateGrade(double average) {
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public void generateOverallReport() {

        List<Student> students =
                studentManager.getAllStudents();

        List<Performance> performances =
                performanceManager.getAllPerformances();

        System.out.println("\n========================================");
        System.out.println("          OVERALL SYSTEM REPORT");
        System.out.println("========================================");

        System.out.println("Total Students      : " + students.size());
        System.out.println("Total Marks Records : " + performances.size());

        if (!performances.isEmpty()) {

            Performance highest =
                    performanceManager.getHighestPerformance();

            Performance lowest =
                    performanceManager.getLowestPerformance();

            System.out.println("----------------------------------------");

            System.out.println("Highest Performance:");
            System.out.println(highest);

            System.out.println("\nLowest Performance:");
            System.out.println(lowest);

            double total = 0;

            for (Performance performance : performances) {
                total += performance.getMarks();
            }

            double average = total / performances.size();

            System.out.printf(
                    "\nOverall Average Marks: %.2f%n",
                    average
            );
        }

        System.out.println("========================================");
    }
}