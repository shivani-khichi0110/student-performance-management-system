public class ValidationTest {

    public static void main(String[] args) {

        int passed = 0;
        int failed = 0;

        System.out.println("=================================");
        System.out.println("       VALIDATION TESTS");
        System.out.println("=================================");

        if (ValidationUtil.isValidMarks(85)) {
            System.out.println("PASS: Valid marks accepted");
            passed++;
        } else {
            System.out.println("FAIL: Valid marks rejected");
            failed++;
        }

        if (!ValidationUtil.isValidMarks(105)) {
            System.out.println("PASS: Marks above 100 rejected");
            passed++;
        } else {
            System.out.println("FAIL: Marks above 100 accepted");
            failed++;
        }

        if (!ValidationUtil.isValidMarks(-10)) {
            System.out.println("PASS: Negative marks rejected");
            passed++;
        } else {
            System.out.println("FAIL: Negative marks accepted");
            failed++;
        }

        if (ValidationUtil.isValidStudentId("STU101")) {
            System.out.println("PASS: Valid student ID accepted");
            passed++;
        } else {
            System.out.println("FAIL: Valid student ID rejected");
            failed++;
        }

        if (!ValidationUtil.isValidStudentId("")) {
            System.out.println("PASS: Empty student ID rejected");
            passed++;
        } else {
            System.out.println("FAIL: Empty student ID accepted");
            failed++;
        }

        if (ValidationUtil.isValidSemester(5)) {
            System.out.println("PASS: Valid semester accepted");
            passed++;
        } else {
            System.out.println("FAIL: Valid semester rejected");
            failed++;
        }

        if (!ValidationUtil.isValidSemester(15)) {
            System.out.println("PASS: Invalid semester rejected");
            passed++;
        } else {
            System.out.println("FAIL: Invalid semester accepted");
            failed++;
        }

        System.out.println("\n=================================");
        System.out.println("Tests Passed: " + passed);
        System.out.println("Tests Failed: " + failed);
        System.out.println("=================================");
    }
}