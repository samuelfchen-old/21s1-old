package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student-unit enrolment system for a university or similar institution
 */
public class EnrolmentSystemImpl implements EnrolmentSystem {
    private ReportGenerator reportGenerator;
    private final List<Faculty> faculties = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    /**
     * Default constructor<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     */
    public EnrolmentSystemImpl(){}

    /**
     * Sets the report generator.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param reportGenerator The report generator to set
     */
    public void setReportGenerator(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    /**
     * Adds a new student to the enrolment system. This new student will not be enrolled in any units to begin with.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * A new student with the matching data will be present in the system.<br>
     *
     * @param s Student to be added, must have a unique sid and unikey
     * @return The new student object that was created and added to the system
     * @throws IllegalStateException if any of the uniqueness requirements are breached.
     * @throws IllegalArgumentException If any of the {@link Student} argument requirements are breached.
     */
    public Student addStudent(Student s) throws IllegalStateException, IllegalArgumentException {
        if (null == s) { throw new IllegalArgumentException(); }

        Student clash = getStudent(s.getSID());
        if (null != clash) throw new IllegalStateException("Student with that SID already exists");

        clash = getStudent(s.getUnikey());
        if (null != clash) throw new IllegalStateException("Student with that unikey already exists");

        // Student s = new StudentImpl(SID, unikey, name, preferredName);
        students.add(s);
        return s;
    }

    /**
     * Adds a new Faculty to the enrolment system.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * A new faculty with the matching data will be present in the system.<br>
     *
     * @param f Faculty to be added, must have unique name
     * @return The new faculty object that was created and added to the system
     * @throws IllegalStateException If any of the uniqueness requirements are breached
     * @throws IllegalArgumentException If any of the {@link Faculty} argument requirements are breached.
     */
    public Faculty addFaculty(Faculty f) throws IllegalStateException, IllegalArgumentException {
        Faculty clash = getFaculty(f.getName());
        if (null != clash) throw new IllegalStateException("Faculty with that name already exists");

        // Faculty f = new FacultyImpl(name, abbreviation);
        faculties.add(f);
        return f;
    }

    /**
     * Adds a new Unit to the enrolment system.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * A new unit with the matching data will be present in the system under the given faculty.<br>
     *
     * @param u Unit to be added, must have a unique code
     * @param f The faculty the unit is to be added to. Must already be stored in the system.
     * @return The Unit that was created and added to the target faculty
     * @throws IllegalStateException If any of the uniqueness requirements are breached. If the target factory name does not exist in this enrolment system
     * @throws IllegalArgumentException If any of the {@link Unit} argument requirements are breached
     */
    public Unit addUnit(Unit u, Faculty f) throws IllegalStateException, IllegalArgumentException {
        // Faculty faculty = getFaculty(facultyName);

        if (null == f) throw new IllegalStateException("No matching faculty");

        // Unit u = new UnitImpl(code, name);
        f.addUnit(u);
        return u;
    }

    /**
     * Retrieves a given faculty from the system<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param name The faculty name to search for
     * @return The faculty with the matching name in this enrolment system, otherwise null
     */
    public Faculty getFaculty(String name) {
        for (Faculty faculty: faculties) {
            if (faculty.getName().equals(name)) return faculty;
        }

        return null;
    }

    /**
     * Retrieves a given student from the system<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param match The term to search for.
     * @return The matching student if one exists, otherwise null. A matching student is one whose SID or unikey matches the given search term.
     */
    public Student getStudent(String match) {
        for (Student student: students) {
            if (student.getUnikey().equals(match)) return student;
            if (student.getSID().equals(match)) return student;
        }

        return null;
    }

    /**
     * Retrieves a list of faculties in this enrolment system.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of faculties represented by their faculty name
     */
    public List<String> getFaculties() {
        List<String> results = new ArrayList<>();

        for (Faculty faculty: faculties) {
            results.add(faculty.getName());
        }

        return results;
    }

    /**
     * Retrieves a list of students in this enrolment system.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of students represented by their unikey
     */
    public List<String> getStudents() {
        List<String> results = new ArrayList<>();

        for (Student student: students) {
            results.add(student.getUnikey());
        }

        return results;
    }

    /**
     * Generates a report on this enrolment system<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A report of the format described in {@link ReportGenerator}
     */
    public String getReport() {
        return reportGenerator.generateReport();
    }
}
