package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a unit of study that students can enrol in
 */
public interface Unit {
    /**
     * Simple getter for unit code<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Unit code.
     */
    public String getCode();

    /**
     * Simple getter for unit name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Unit name.
     */
    public String getName();

    /**
     * Enrols the given student in this unit<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * The student will be enrolled in this unit.<br>
     *
     * @param student The student to enrol. May not be null. May not already be enrolled in this unit.
     * @throws IllegalArgumentException If student is null
     * @throws IllegalStateException If a matching student (by equal SID or unikey) is enrolled already
     */
    public void enrolStudent(Student student) throws IllegalArgumentException, IllegalStateException;

    /**
     * Withdraws the given student from this unit<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * The given student will be withdrawn from this unit.<br>
     *
     * @param student The student to withdraw. May not be null. Must currently be enrolled.
     * @throws IllegalArgumentException If student is null
     * @throws IllegalStateException if no matching student (by equal SID or unikey) is currently enrolled
     */
    public void withdrawStudent(Student student) throws IllegalArgumentException, IllegalStateException;

    /**
     * Retrieves a list of students enrolled in this unit.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of students represented by their unikey
     */
    public List<String> getStudents();

    /**
     * Retrieves a given enrolled student from the unit<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param match The term to search for.
     * @return The matching enrolled student if one exists, otherwise null. A matching student is one whose SID or unikey matches the given search term.
     */
    public Student getStudent(String match);
}
