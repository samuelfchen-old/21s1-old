package au.edu.sydney.soft3202.s1y2021.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a faculty or equivalent department that contains units of study
 */
public interface Faculty {

    /**
     * Simple getter for name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Faculty name
     */
    public String getName();

    /**
     * Simple getter for abbreviation<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Faculty abbreviation
     */
    public String getAbbreviation();

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
    public void addUnit(Unit unit) throws IllegalStateException;

    /**
     * Retrieves the matching unit from this faculty.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     * @param unitCode The unit code to search for
     * @return The unit in this faculty with an equal unit code, or null if no match is found
     */
    public Unit getUnit(String unitCode);

    /**
     * Retrieves a list of units in this faculty.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of units represented by their code
     */
    public List<String> getUnits();
}
