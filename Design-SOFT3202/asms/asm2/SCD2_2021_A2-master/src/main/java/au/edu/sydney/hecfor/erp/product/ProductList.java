package au.edu.sydney.hecfor.erp.product;

import java.util.List;

/**
 * This class provides access to Product module services to a consumer. Creates, stores, and manages Products.
 */
public interface ProductList {

    /**
     * Creates and adds a new product to the list. Note that no approval status is set by default.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * An product with the given details will be added to the list.<br>
     *
     * @param id The id of the product to add. Must be positive, may not be zero. Must be unique in this list.
     * @param name The name of the product to add. May not be null or empty.
     * @param manufacturer The manufacturer of the product to add. May not be null or empty.
     * @return The product that was created and added.
     * @throws IllegalStateException If a product with the provided ID already exists in this list.
     * @throws IllegalArgumentException If any argument requirements are breached
     */
    Product addProduct(int id, String name, String manufacturer) throws IllegalStateException, IllegalArgumentException;

    /**
     * Empties the list.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * The list will be empty.<br>
     */
    void clear();

    /**
     * Gets all products in the list.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of all products in the list. Will not be null. Will be empty if no products exist.
     */
    List<Product> findAll();

    /**
     * Gets all products matching the given approval finalised status in the list.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param finalised The approval finalised status to match.
     * @return A list of all products with a matching approval finalised status. Will not be null. Will be empty if no products match.
     */
    List<Product> findAll(boolean finalised);

    /**
     * Gets all products matching the given field terms in the list.<br>
     * The 'field' parameter determines whether to search by 'approval' status or 'manufacturer' name.<br>
     * The terms are the elements in that field to search for. Only exact matches need to be included.<br>
     * For example, a search of ("manufacturer", "Pfizer", "AstraZeneca") will yield all products manufactured<br>
     * by either Pfizer or AstraZeneca. Search is case sensitive - you can just assume it will naively use .equals()<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param field The field to match on. Must be one of either "manufacturer" or "approval".
     * @param terms The search terms to match on.
     * @throws IllegalArgumentException If any argument requirements are breached
     * @return A list of all products assigned to the given departments. Will not be null. Will be empty if no products match.
     */
    List<Product> findAll(String field, String... terms);

    /**
     * Retrieves a specific product by id.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param id The product id to retrieve. Must be positive, may not be zero.
     * @return The matching product, or null if no match found.
     * @throws IllegalArgumentException If any argument requirements are breached
     */
    Product findOne(int id) throws IllegalArgumentException;

    /**
     * Removes a specific product by id.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * Any product matching the given ID will no longer exist in the list.<br>
     *
     * @param id The product id to remove. Must be positive, may not be zero.
     * @return True if a match was removed, otherwise false.
     * @throws IllegalArgumentException If any argument requirements are breached
     */
    boolean remove(int id) throws IllegalArgumentException;
}
