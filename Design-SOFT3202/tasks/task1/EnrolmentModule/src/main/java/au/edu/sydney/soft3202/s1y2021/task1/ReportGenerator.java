package au.edu.sydney.soft3202.s1y2021.task1;

/**
 * Utility service provider for compiling reports for an EnrolmentSystem
 */
public class ReportGenerator {
    private final EnrolmentSystem enrolmentSystem;

    /**
     * Constructor.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * The report generator will be constructed paired with the given enrolment system.<br>
     *
     * @param enrolmentSystem The enrolment system to generate reports for. May not be null.
     * @throws IllegalArgumentException If any parameter conditions are breached
     */
    public ReportGenerator(EnrolmentSystem enrolmentSystem) throws IllegalArgumentException {
        if (null == enrolmentSystem) throw new IllegalArgumentException("EnrolmentSystem may not be null.");
        this.enrolmentSystem = enrolmentSystem;
    }

    /**
     * Generates a report based on the paired enrolment system.<br>
     * The report is a list of Students, Faculties, Units, and enrolled Students in the following format:<br>
     * <pre>
     * {@code
     * Students:
     * \t<Student>
     * \t<Student>
     * <Faculty>
     * \t<Unit>
     * \t\t<enrolled Student>
     * \t\t<enrolled Student>
     * \t<Unit>
     * \t\t<enrolled Student>
     * \t\t<enrolledStudent>
     * <Faculty>
     * \t<Unit>
     * \t\t<enrolled Student>
     * \t\t<enrolled Student>
     * \t<Unit>
     * \t\t<enrolled Student>
     * \t\t<enrolledStudent>
     * }
     * <br><br>
     * And where the objects referred to in {@code <angle brackets>} are represented in the following formats: <br><br>
     * Student: SID unikey name preferredname<br>
     * Faculty: abbreviation name<br>
     * Unit: code name<br><br>
     * All attributes are separated with a single space. In the case of a null preferred name there will be a
     * trailing separator space with no characters where the preferred name would otherwise be.<br>
     * Newlines are \n.
     * </pre>
     * <br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The report as a multi-line string.
     */
    public String generateReport() {
        StringBuilder sb = new StringBuilder();

        sb.append(makeIndentedLine(0,"Students:"));

        for (String unikey: enrolmentSystem.getStudents()) {
            Student student = enrolmentSystem.getStudent(unikey);
            sb.append(makeIndentedLine(1,
                    student.getSID(),
                    student.getUnikey(),
                    student.getName(),
                    student.getPreferredName()));
        }

        for (String facultyName: enrolmentSystem.getFaculties()) {
            Faculty faculty = enrolmentSystem.getFaculty(facultyName);
            sb.append(makeIndentedLine(0, faculty.getAbbreviation(), faculty.getName()));
            for (String unitCode: faculty.getUnits()) {
                Unit unit = faculty.getUnit(unitCode);
                sb.append(makeIndentedLine(1, unit.getCode(), unit.getName()));
                for (String unikey: unit.getStudents()) {
                    Student student = unit.getStudent(unikey);
                    sb.append(makeIndentedLine(2,
                            student.getSID(),
                            student.getUnikey(),
                            student.getName(),
                            null == student.getPreferredName() ? "" : student.getPreferredName()));
                }
            }
        }


        return sb.toString();
    }

    private String makeIndentedLine(int indentLevel, String... text) {
        return "\t".repeat(Math.max(0, indentLevel)) +
                String.join(" ",text) +
                "\n";
    }
}
