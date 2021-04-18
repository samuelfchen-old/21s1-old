package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student-unit enrolment system for a university or similar institution
 */
public interface EnrolmentSystem {

    /**
     * Sets the report generator.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param reportGenerator The report generator to set
     */
    public void setReportGenerator(ReportGenerator reportGenerator);

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
    public Student addStudent(Student s) throws IllegalStateException, IllegalArgumentException;

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
    public Faculty addFaculty(Faculty f) throws IllegalStateException, IllegalArgumentException;

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
    public Unit addUnit(Unit u, Faculty f) throws IllegalStateException, IllegalArgumentException;

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
    public Faculty getFaculty(String name);

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
    public Student getStudent(String match);

    /**
     * Retrieves a list of faculties in this enrolment system.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of faculties represented by their faculty name
     */
    public List<String> getFaculties();

    /**
     * Retrieves a list of students in this enrolment system.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of students represented by their unikey
     */
    public List<String> getStudents();

    /**
     * Generates a report on this enrolment system<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A report of the format described in {@link ReportGenerator}
     */
    public String getReport();
}
