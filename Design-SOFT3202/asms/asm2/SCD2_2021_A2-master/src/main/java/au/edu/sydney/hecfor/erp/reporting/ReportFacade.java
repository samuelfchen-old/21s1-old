package au.edu.sydney.hecfor.erp.reporting;


import au.edu.sydney.hecfor.erp.auth.AuthToken;

public interface ReportFacade {

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * The storage count will be reported and recorded<br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param storageID The storage ID to report for. Must be greater than 0.
     * @param productName The stored product name. May not be null or empty.
     * @param location The storage location. May not be null or empty.
     * @param count The current count of the storage - may not be negative (may be zero).
     * @throws SecurityException If the authentication token is null or fails verification.
     * @throws IllegalArgumentException If any of the other preconditions are violated.
     */
    void makeReport(AuthToken token, int storageID, String productName, String location, int count) throws IllegalArgumentException, SecurityException;

    /**
     * <b>Preconditions:</b><br>
     * <br>
     * <b>Postconditions:</b><br>
     * <br>
     *
     * @param token The authentication token to verify this operation with. May not be null.
     * @param storageID The storage ID to get the last reported count of. May not be null or empty. Must be greater than 0.
     * @return If no reports have been generated returns -1 - otherwise returns the last reported count for this storage
     * @throws SecurityException If the authentication token is null or fails verification.
     * @throws IllegalStateException If the given (valid) storage ID does not match a known storage.
     * @throws IllegalArgumentException If any of the other preconditions are violated.
     */
    int getLastReportedCount(AuthToken token, int storageID) throws IllegalArgumentException, SecurityException;
}
