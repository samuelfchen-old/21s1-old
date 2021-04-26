package au.edu.sydney.hecfor.erp.tgr;

import au.edu.sydney.hecfor.erp.product.Product;
import au.edu.sydney.hecfor.erp.product.ProductList;

import java.util.List;

/**
 * The main access view for users of the TGR module.
 */
public interface TGRFacade {
    /**
     * Adds a new product to the system.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * A new Product with the given information will be present in the system.<br>
     *
     * @param name The product name. May not be null or empty.
     * @param manufacturer The manufacturer. May not be null or empty.
     * @return The Product that has been added.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider
     * @throws IllegalArgumentException If any argument requirements are breached
     */
    Product addProduct(String name, String manufacturer) throws IllegalStateException, IllegalArgumentException;

    /**
     * Sets a Product to an approval status. Can be a step on an approval process or a final outcome.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * The matching product will be set to the given approval status.<br>
     *
     * @param productID The product to assign. Must be positive (may not be zero).
     * @param approvalStatus The approval status to assign. Must be one of the following: "LAB TESTING", "CLINICAL TRIALS", "APPROVED", "REJECTED".<br>
     * If the status is "APPROVED" or "REJECTED" the approval is considered finalised.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) product ID does not match a product <i>that does not have finalised approval</i> in the current product provider.
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    void assignApprovalStatus(int productID, String approvalStatus) throws IllegalStateException, IllegalArgumentException;

    /**
     * Returns a list of all current product represented by "Manufacturer: Name"<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of all current products represented by "Manufacturer: Name". In the case no products can be found, returns an empty list.
     */
    List<String> viewAllProducts();

    /**
     * Returns a list of all current products.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of all current products.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider.
     */
    List<Product> getAllProducts() throws IllegalStateException;

    /**
     * Adds a stored amount of the given product<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * An amount of the given product will be stored with the provided details.<br>
     *
     * @param storageID The storage ID must be unique, may not be negative, may not be zero. The storage ID may be null, in which case a generated unique ID will be used. This is only available so long as all previous storage IDs have been null - if an ID is provided, all <i>future</i> calls must also provide a unique ID.
     * @param productID The product to store. Must be positive (may not be zero).
     * @param count The amount of the product to store. Must be positive (may be zero).
     * @param location The location of the storage. May not be empty or null.
     * @return The id of the created storage.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) product ID does not match a product in the current product provider.
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    int addStorage(Integer storageID, int productID, int count, String location) throws IllegalStateException, IllegalArgumentException;

    /**
     * Returns a list of all current product storage.<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @return A list of all current storage represented by "ProductID-StorageID: count (location)". In the case no storage can be found, returns an empty list.
     */
    List<String> getStorages();

    /**
     * Returns a list of all current storage matching the given product.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param productID The product to match. Must be positive (may not be zero).
     * @return A list of all current storage IDs matching the given product. In the case no storage can be found, returns an empty list.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) product ID does not match a product in the current product provider.
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    List<Integer> getStorages(int productID) throws IllegalStateException, IllegalArgumentException;

    /**
     * Gets the location of the given storage.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param id The storage to retrieve. Must be positive, may not be zero.
     * @return The location of the storage
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) storage ID does not match a known storage
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    String getStorageLocation(int id) throws IllegalStateException, IllegalArgumentException;

    /**
     * Gets the count of the given storage.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * None<br>
     *
     * @param id The storage id to retrieve. Must be positive, may not be zero.
     * @return The count of the product in storage.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) storage ID does not match a known storage
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    int getStorageCount(int id) throws IllegalStateException, IllegalArgumentException;

    /**
     * Sets the count of the given storage.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * The selected storage count will reflect the new value.<br>
     *
     * @param id The storage id to modify. Must be positive, may not be zero.
     * @param count The new count of product in the given storage. May not be negative, may be zero.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) storage ID does not match a known storage
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    void setStorageCount(int id, int count) throws IllegalStateException, IllegalArgumentException;

    /**
     * Modifies the count of the given storage.<br><br>
     * <b>Preconditions:</b><br>
     * The TGRFacade must have been injected with a non-null instance of a Product module via TGRFacade.setProductProvider<br>
     * <b>Postconditions:</b><br>
     * The selected storage count will reflect the modification.<br>
     *
     * @param id The storage id to modify. Must be positive, may not be zero.
     * @param modifier The modification to be made. May be a positive or negative number. May not reduce the final count below zero.
     * @throws IllegalStateException If the TGRFacade has a null ProductProvider. If the given (valid) storage ID does not match a known storage.
     * @throws IllegalArgumentException If any of the argument requirements are breached.
     */
    void modifyStorageCount(int id, int modifier) throws IllegalStateException, IllegalArgumentException;

    /**
     * Injects a product module provider to the system<br><br>
     * <b>Preconditions:</b><br>
     * None<br>
     * <b>Postconditions:</b><br>
     * The system will call the given provider for all product related operations<br>
     * All storages will be cleared, and the null ID state for new storages will be reset.<br>
     *
     * @param provider The provider to inject. May be null, however this will result in some product related operations throwing exceptions.
     */
    void setProductProvider(ProductList provider);
}
