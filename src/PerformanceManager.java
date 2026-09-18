import java.util.ArrayList;
import java.util.List;

public class PerformanceManager {

    private final List<Performance> performances;
    private final FileManager fileManager;

    public PerformanceManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.performances =
                new ArrayList<>(fileManager.loadPerformances());
    }

    public boolean addPerformance(Performance performance) {
        performances.add(performance);
        fileManager.savePerformances(performances);
        return true;
    }

    public List<Performance> getAllPerformances() {
        return new ArrayList<>(performances);
    }

    public List<Performance> getPerformancesByStudent(String studentId) {
        List<Performance> result = new ArrayList<>();

        for (Performance performance : performances) {
            if (performance.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                result.add(performance);
            }
        }

        return result;
    }

    public double calculateAverage(String studentId) {
        List<Performance> studentPerformance =
                getPerformancesByStudent(studentId);

        if (studentPerformance.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Performance performance : studentPerformance) {
            total += performance.getMarks();
        }

        return total / studentPerformance.size();
    }

    public double calculateTotal(String studentId) {
        double total = 0;

        for (Performance performance : performances) {
            if (performance.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                total += performance.getMarks();
            }
        }

        return total;
    }

    public boolean deleteByStudentId(String studentId) {
        boolean removed = performances.removeIf(
                p -> p.getStudentId().equalsIgnoreCase(studentId)
        );

        if (removed) {
            fileManager.savePerformances(performances);
        }

        return removed;
    }

    public Performance getHighestPerformance() {
        if (performances.isEmpty()) {
            return null;
        }

        Performance highest = performances.get(0);

        for (Performance performance : performances) {
            if (performance.getMarks() > highest.getMarks()) {
                highest = performance;
            }
        }

        return highest;
    }

    public Performance getLowestPerformance() {
        if (performances.isEmpty()) {
            return null;
        }

        Performance lowest = performances.get(0);

        for (Performance performance : performances) {
            if (performance.getMarks() < lowest.getMarks()) {
                lowest = performance;
            }
        }

        return lowest;
    }
}