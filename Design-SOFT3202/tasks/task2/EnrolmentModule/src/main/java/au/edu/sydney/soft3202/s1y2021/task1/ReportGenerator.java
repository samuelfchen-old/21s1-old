package au.edu.sydney.soft3202.s1y2021.task1;

/**
 * Utility service provider for compiling reports for an EnrolmentSystem
 */
public interface ReportGenerator {
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
    public String generateReport();
}
