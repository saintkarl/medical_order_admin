package com.karlchu.mo.core.data.session;

import javax.ejb.DuplicateKeyException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericSessionBean<T, ID extends Serializable> {
	/**
	 * Perform an initial save of a previously unsaved T entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            T entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public T save(T entity) throws DuplicateKeyException;

	/**
	 * Delete a persistent T entity.
	 * 
	 * @param id
	 *            the ID of T entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ID id) throws RemoveException;


    public void delete(T entity) throws RemoveException;
    /**
     * Delete a persistent T entity.
     *
     * @param ids
     *            the list ID of T entities to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public Integer delete(ID[] ids) throws RemoveException;

	/**
	 * Persist a previously saved T entity and return it or a copy of it to
	 * the sender. A copy of the T entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            T entity to update
	 * @return T the persisted T entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public T update(T entity) throws DuplicateKeyException;

	public T findById(ID id) throws ObjectNotFoundException;

    public T findEqualUnique(String property, Object value) throws ObjectNotFoundException;

    public T findEqualUniqueCaseSensitive(String property, Object value) throws ObjectNotFoundException;
	/**
	 * Find all T entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the T property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<T> found by query
	 */
	public List<T> findByProperty(String propertyName, Object value,
                                  int... rowStartIdxAndCount);

	/**
	 * Find all T entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<T> all T entities
	 */
	public List<T> findAll(int... rowStartIdxAndCount);

    public Object[] searchByProperties(Map<String, Object> properties,
                                       String sortExpression, String sortDirection, Integer offset,
                                       Integer limit, String whereClause);

    public Object[] searchByProperties(Map<String, Object> properties,
                                       String sortExpression, String sortDirection, Integer offset,
                                       Integer limit);
    
    public Object findByProperties(Map<String, Object> properties,
                                   String sortExpression, String sortDirection, Integer offset,
                                   Integer limit);
    /**
     * Count all records in database
     * @return number of records
     */
    public Long countByProperties(Map<String, Object> properties);

    public List<T> findProperty(String property, Object value);

    void deleteAll(List<T> entities);

    List<T> findProperties(Map<String, Object> properties);
}
