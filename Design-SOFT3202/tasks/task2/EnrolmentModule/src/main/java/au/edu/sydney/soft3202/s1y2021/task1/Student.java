package au.edu.sydney.soft3202.s1y2021.task1;

/**
 * Represents a student that can be added to an EnrolmentSystem and enrolled in Units
 */
public interface Student {
    /**
     * Simple getter for SID<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student SID
     */
    public String getSID();

    /**
     * Simple getter for unikey<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student unikey
     */
    public String getUnikey();

    /**
     * Simple getter for name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student name
     */
    public String getName();

    /**
     * Simple getter for preferred name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student preferred name. May be null.
     */
    public String getPreferredName();

    /**
     * Setter for preferred name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param preferredName The new value for preferred name. May be null or empty. May not be more than 16 characters in length.
     * @throws IllegalArgumentException If the parameter requirements are breached.
     */
    public void setPreferredName(String preferredName) throws IllegalArgumentException;
}
