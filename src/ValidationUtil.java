public class ValidationUtil {

    private ValidationUtil() {
        // Utility class
    }

    public static boolean isValidStudentId(String id) {
        return id != null && id.trim().matches("[A-Za-z0-9_-]{2,20}");
    }

    public static boolean isValidName(String name) {
        return name != null
                && !name.trim().isEmpty()
                && name.trim().matches("[A-Za-z ]{2,50}");
    }

    public static boolean isValidCourse(String course) {
        return course != null
                && !course.trim().isEmpty()
                && course.length() <= 50;
    }

    public static boolean isValidSemester(int semester) {
        return semester >= 1 && semester <= 12;
    }

    public static boolean isValidSubject(String subject) {
        return subject != null
                && !subject.trim().isEmpty()
                && subject.length() <= 50;
    }

    public static boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }
}