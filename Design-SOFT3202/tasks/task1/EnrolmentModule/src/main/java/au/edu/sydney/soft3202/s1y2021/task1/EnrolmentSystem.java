package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student-unit enrolment system for a university or similar institution
 */
public class EnrolmentSystem {
    private final ReportGenerator reportGenerator = new ReportGenerator(this);
    private final List<Faculty> faculties = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    /**
     * Default constructor<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     */
    public EnrolmentSystem(){}

    /**
     * Adds a new student to the enrolment system. This new student will not be enrolled in any units to begin with.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * A new student with the matching data will be present in the system.<br>
     *
     * @param SID Must match the SID requirements of {@link Student}. Must be unique in this EnrolmentSystem
     * @param unikey Must match the unikey requirements of {@link Student}. Must be unique in this EnrolmentSystem
     * @param name Must match the name requirements of {@link Student}
     * @param preferredName Must match the preferred name requirements of {@link Student}
     * @return The student object that was created and added to the system.
     * @throws IllegalStateException if any of the uniqueness requirements are breached.
     * @throws IllegalArgumentException If any of the {@link Student} argument requirements are breached.
     */
    public Student addStudent(String SID, String unikey, String name, String preferredName) throws IllegalStateException, IllegalArgumentException {
        Student clash = getStudent(SID);
        if (null != clash) throw new IllegalStateException("Student with that SID already exists");

        clash = getStudent(unikey);
        if (null != clash) throw new IllegalStateException("Student with that unikey already exists");

        Student s = new Student(SID, unikey, name, preferredName);
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
     * @param name Must match the name requirements of {@link Faculty}. Must be unique in this EnrolmentSystem.
     * @param abbreviation Must match the abbreviation requirements of requirements of {@link Faculty}
     * @return The new faculty object that was created and added to the system
     * @throws IllegalStateException If any of the uniqueness requirements are breached
     * @throws IllegalArgumentException If any of the {@link Faculty} argument requirements are breached.
     */
    public Faculty addFaculty(String name, String abbreviation) throws IllegalStateException, IllegalArgumentException {
        Faculty clash = getFaculty(name);
        if (null != clash) throw new IllegalStateException("Faculty with that name already exists");

        Faculty f = new Faculty(name, abbreviation);
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
     * @param code Must match the requirements of {@link Unit}. Must be unique in the target faculty.
     * @param name Must match the requirements of {@link Unit}
     * @param facultyName Must match an existing faculty in this enrolment system
     * @return The Unit that was created and added to the target faculty
     * @throws IllegalStateException If any of the uniqueness requirements are breached. If the target factory name does not exist in this enrolment system
     * @throws IllegalArgumentException If any of the {@link Unit} argument requirements are breached
     */
    public Unit addUnit(String code, String name, String facultyName) throws IllegalStateException, IllegalArgumentException {


        Faculty faculty = getFaculty(facultyName);

        if (null == faculty) throw new IllegalStateException("No matching faculty");

        Unit u = new Unit(code, name);
        faculty.addUnit(u);
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
