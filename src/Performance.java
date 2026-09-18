public class Performance {
    private String studentId;
    private String subject;
    private double marks;

    public Performance(String studentId, String subject, double marks) {
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSubject() {
        return subject;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public String toFileString() {
        return studentId + "|" + subject + "|" + marks;
    }

    public static Performance fromFileString(String line) {
        String[] parts = line.split("\\|");

        if (parts.length != 3) {
            return null;
        }

        try {
            double marks = Double.parseDouble(parts[2]);
            return new Performance(parts[0], parts[1], marks);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Student ID: %-10s | Subject: %-20s | Marks: %6.2f | Grade: %s",
            studentId, subject, marks, getGrade()
        );
    }
}