package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a unit of study that students can enrol in
 */
public class UnitImpl implements Unit {
    private final List<Student> students = new ArrayList<>();

    private final String code;
    private final String name;

    /**
     * Constructor.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param code Must be precisely 4 alphabetic characters (a-zA-Z) followed by 4 digits.
     * @param name Must be less than or equal to 64 characters. May not be null.
     * @throws IllegalArgumentException If any parameter preconditions are violated.
     */
    public UnitImpl(String code, String name) throws IllegalArgumentException {
        if (null == code) throw new IllegalArgumentException("Code may not be null");
        if (null == name) throw new IllegalArgumentException("Name may not be null");

        if (code.length() != 8) throw new IllegalArgumentException("Code must be 4 letters and 4 numbers");
        if (!code.substring(0,3).chars().allMatch(Character::isAlphabetic) &&
                !code.substring(4).chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Code must be 4 letters and 4 numbers");
        }

        if (name.length() > 64) throw new IllegalArgumentException("Name cannot be more than 64 characters");

        this.code = code;
        this.name = name;
    }

    /**
     * Simple getter for unit code<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Unit code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Simple getter for unit name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Unit name.
     */
    public String getName() {
        return name;
    }

    /**
     * Remember you don't need to test private methods! You automatically test them if you properly
     * test the public methods of the class.
     *
     * You will sometimes want to test private methods to get better visibility on where bugs are in
     * very complex classes, but you don't need to for assessment purposes, just the documented public API.
     *
     * @param student Ignore me
     * @return Ignore me
     */
    private Student checkEnrolledStudent(Student student) {
        if (null == student) throw new NullPointerException();

        for (Student enrolled: students) {
            if (enrolled.getSID().equals(student.getSID())) return enrolled;
            if (enrolled.getUnikey().equals(student.getUnikey())) return enrolled;
        }

        return null;
    }

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
    public void enrolStudent(Student student) throws IllegalArgumentException, IllegalStateException {
        if (null == student) throw new IllegalArgumentException("Student may not be null");
        if (null != checkEnrolledStudent(student)) throw new IllegalStateException("Student is already enrolled");

        students.add(student);
    }

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
    public void withdrawStudent(Student student) throws IllegalArgumentException, IllegalStateException {
        if (null == student) throw new IllegalArgumentException("Student may not be null");
        if (null == checkEnrolledStudent(student)) throw new IllegalStateException("Student is not enrolled");

        students.remove(student);
    }

    /**
     * Retrieves a list of students enrolled in this unit.<br><br>
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
     * Retrieves a given enrolled student from the unit<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param match The term to search for.
     * @return The matching enrolled student if one exists, otherwise null. A matching student is one whose SID or unikey matches the given search term.
     */
    public Student getStudent(String match) {
        for (Student student: students) {
            if (student.getUnikey().equals(match)) return student;
            if (student.getSID().equals(match)) return student;
        }

        return null;
    }
}
