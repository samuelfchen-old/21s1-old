package au.edu.sydney.hecfor.erp.product;

/**
 * This class is a record of a type of Product that can be stored by the system.
 */
public interface Product {

    /**
     * Sets this Product object to a new approval status. Approval statuses are simple String objects.
     * Some approval statuses are marked as 'finalised' and cannot be changed further.<br><br>
     * <b>Preconditions:</b><br>
     * The Product must not have had a 'finalised' approval status set previously.<br>
     * <b>Postconditions:</b><br>
     * The Product will be set to the given approval status. If the finalised parameter is true it will
     * be marked as final and no further updates may be made.<br>
     *
     * @param approvalStatus The approval status to assign. May not be empty or null.
     * @param finalised Whether this is a final step in the approval process
     * @throws IllegalStateException If this product has already been assigned to a final approval status.
     * @throws IllegalArgumentException If any argument requirements are breached
     */
    void setApprovalStatus(String approvalStatus, boolean finalised) throws IllegalStateException, IllegalArgumentException;

    /**
     * Simple accessor for product approval finalised status.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return True if the product has been assigned to a final approval status, otherwise false.
     */
    boolean approvalFinalised();

    /**
     * Simple accessor for product assigned approval status<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The approval status the product has been assigned to. Will be null if no approval status has been set.
     */
    String getApprovalStatus();

    /**
     * Simple accessor for product id<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The product ID. Will not be negative or zero.
     */
    int getID();

    /**
     * Simple accessor for product name<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The product name. Will not be null or empty.
     */
    String getName();

    /**
     * Simple accessor for product manufacturer<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return The product manufacturer. Will not be null or empty.
     */
    String getManufacturer();
}
