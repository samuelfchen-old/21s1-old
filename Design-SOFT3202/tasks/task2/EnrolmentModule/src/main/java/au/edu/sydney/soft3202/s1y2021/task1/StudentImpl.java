package au.edu.sydney.soft3202.s1y2021.task1;

/**
 * Represents a student that can be added to an EnrolmentSystem and enrolled in Units
 */
public class StudentImpl implements Student {
    private final String SID;
    private final String unikey;
    private final String name;
    private String preferredName;


    /**
     * Constructor.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param SID Must be exactly 9 digits and may not start with 0.
     * @param unikey Must be precisely 4 alphabetic characters (a-zA-Z) followed by 4 digits.
     * @param name Must be less than or equal to 64 characters. May not be null.
     * @param preferredName Must be less than or equal to 16 characters. May not be null.
     */
    public StudentImpl(String SID, String unikey, String name, String preferredName) {

        if (null == SID) throw new IllegalArgumentException("SID may not be null");
        if (null == unikey) throw new IllegalArgumentException("Unikey may not be null");
        if (null == name) throw new IllegalArgumentException("Name may not be null");

        if (SID.length() != 9 || SID.charAt(0) == '0' || !SID.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("SID must be 9 digits and may not start with 0");
        }

        if (!unikey.substring(0,3).chars().allMatch(Character::isAlphabetic) &&
                !unikey.substring(4).chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Unikey must be 4 letters and 4 numbers");
        }

        if (name.length() > 64) throw new IllegalArgumentException("Name cannot be more than 64 characters");
        if (null != preferredName && preferredName.length() > 16) throw new IllegalArgumentException("Preferred name cannot be more than 16 characters");

        this.SID = SID;
        this.unikey = unikey;
        this.name = name;
        this.preferredName = preferredName;
    }

    /**
     * Simple getter for SID<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student SID
     */
    public String getSID() {
        return SID;
    }

    /**
     * Simple getter for unikey<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student unikey
     */
    public String getUnikey() {
        return unikey;
    }

    /**
     * Simple getter for name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student name
     */
    public String getName() {
        return name;
    }

    /**
     * Simple getter for preferred name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The Student preferred name. May be null.
     */
    public String getPreferredName() {
        return preferredName;
    }

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
    public void setPreferredName(String preferredName) throws IllegalArgumentException {
        if (null != preferredName && preferredName.length() > 16) throw new IllegalArgumentException("Preferred name cannot be more than 16 characters");

        this.preferredName = preferredName;
    }
}
