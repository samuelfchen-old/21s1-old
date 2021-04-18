package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a faculty or equivalent department that contains units of study
 */
public class FacultyImpl implements Faculty{
    private final List<Unit> units = new ArrayList<>();

    private final String name;
    private final String abbreviation;
    /**
     * Constructor.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param name Must be less than or equal to 64 characters. May not be null or empty.
     * @param abbreviation Must be less than or equal to 10 characters. May not be null or empty.
     * @throws IllegalArgumentException If any parameter preconditions are violated.
     */
    public FacultyImpl(String name, String abbreviation) throws IllegalArgumentException {
        if (null == name || "".equals(name)) throw new IllegalArgumentException("Name may not be null or empty");
        if (null == abbreviation || "".equals(abbreviation)) throw new IllegalArgumentException("Abbreviation may not be null or empty");

        if (name.length() > 64) throw new IllegalArgumentException("Name cannot be more than 64 characters");
        if (abbreviation.length() > 10) throw new IllegalArgumentException("Abbreviation cannot be more than 10 characters");

        this.name = name;
        this.abbreviation = abbreviation;
    }


    /**
     * Simple getter for name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Faculty name
     */
    public String getName() {
        return name;
    }

    /**
     * Simple getter for abbreviation<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Faculty abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Remember you don't need to test private methods! You automatically test them if you properly
     * test the public methods of the class.
     *
     * You will sometimes want to test private methods to get better visibility on where bugs are in
     * very complex classes, but you don't need to for assessment purposes, just the documented public API.
     *
     * @param unit Ignore me
     * @return Ignore me
     * @throws NullPointerException Ignore me
     */
    private boolean checkUnit(Unit unit) throws NullPointerException {
        if (null == unit) throw new NullPointerException();

        for (Unit added: units) {
            if (added.getCode().equals(unit.getCode())) return true;
        }

        return false;
    }

    /**
     * Adds a unit to this faculty.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param unit The unit to add. May not be null. May not already exist in this Faculty (based on unit code equality)
     * @throws IllegalStateException If a duplicate unit exists in this Faculty
     * @throws IllegalArgumentException If any other parameter preconditions are violated.
     */
    public void addUnit(Unit unit) throws IllegalStateException {
        if (null == unit) throw new IllegalArgumentException("Unit may not be null");
        if (checkUnit(unit)) throw new IllegalStateException("Unit already exists in faculty.");

        units.add(unit);
    }

    /**
     * Retrieves the matching unit from this faculty.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     * @param unitCode The unit code to search for
     * @return The unit in this faculty with an equal unit code, or null if no match is found
     */
    public Unit getUnit(String unitCode) {
        for (Unit unit: units) {
            if (unit.getCode().equals(unitCode)) return unit;
        }

        return null;
    }

    /**
     * Retrieves a list of units in this faculty.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of units represented by their code
     */
    public List<String> getUnits() {
        List<String> results = new ArrayList<>();

        for (Unit unit: units) {
            results.add(unit.getCode());
        }

        return results;
    }
}
