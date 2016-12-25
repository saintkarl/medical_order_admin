/**
 * 
 */
package com.karlchu.mo.core.data.session.imp;



import com.karlchu.mo.common.util.HibernateUtil;
import com.karlchu.mo.core.data.session.GenericSessionBean;

import javax.ejb.DuplicateKeyException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Vien Nguyen
 *
 */
public abstract class AbstractSessionBean<T, ID extends Serializable> implements GenericSessionBean<T, ID> {
	private transient static Logger logger = Logger.getLogger("SessionBean");
	
	private Class<T> persistentClass;
	
	public AbstractSessionBean() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
    *
    * @return
    */
	public Class<T> getPersistentClass() {
       return persistentClass;
	}
	/**
	 * 
	 * @return
	 */
	public String getPersistentClassName() {
       return persistentClass.getSimpleName();
	}

   /**
    *
    * @param persistentClass
    */
	protected AbstractSessionBean(Class<T> persistentClass) {
       this.persistentClass = persistentClass;
	}
    
	
	@PersistenceContext(unitName = "persistence-data")
	protected EntityManager entityManager;
	
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
	public T save(T entity) throws DuplicateKeyException {
		logger.log(Level.INFO, "saving T instance");
		try {
			entityManager.persist(entity);
			logger.log(Level.INFO, "save successful");
			return entity;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "save failed", re);
			throw re;
		}
	}

	/**
	 * Delete a persistent T entity.
	 * 
	 * @param id
	 *            T entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ID id) throws RemoveException {
		logger.log(Level.INFO, "deleting T instance");
		try {
			T entity = entityManager.getReference(getPersistentClass(), id);
			entityManager.remove(entity);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

    public void delete(T entity) throws RemoveException {
        logger.log(Level.INFO, "deleting T instance");
        try {
            entityManager.remove(entity);
            entityManager.flush();
            logger.log(Level.INFO, "delete successful");
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "delete failed", re);
            throw re;
        }
    }

    /**
     * Delete a persistent T entity.
     *
     * @param ids
     *            List of ID of entities to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public Integer delete(ID[] ids) throws RemoveException {
        logger.log(Level.INFO, "deleting T instances");
        Integer res = 0;
        for (ID id : ids) {
            try {

                T entity = entityManager.getReference(getPersistentClass(), id);
                entityManager.remove(entity);
                res++;
            } catch (RuntimeException re) {
                logger.log(Level.SEVERE, "delete failed " + id, re);
                throw re;
            }
        }
        return res;
    }

    public void deleteAll(List<T> entities) {
        try{
            for (T entity : entities) {
                entityManager.remove(entity);
            }
            entityManager.flush();
        }catch (RuntimeException re) {
            throw re;
        }
    }

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
	public T update(T entity) throws DuplicateKeyException {
		logger.log(Level.INFO, "updating T instance");
		try {
			T result = entityManager.merge(entity);
			logger.log(Level.INFO, "update successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "update failed", re);
			throw re;
		}
	}

	public T findById(ID id) throws ObjectNotFoundException {
		logger.log(Level.INFO, "finding T instance with id: " + id);
		try {
			T instance = entityManager.find(getPersistentClass(), id);
            if (instance == null) {
                throw new ObjectNotFoundException("Not found T " + id);
            }
			return instance;
		} catch (RuntimeException re) {
			//logger.log(Level.SEVERE, "find failed", re);
			throw new ObjectNotFoundException("Not found T " + id);
		}
	}

    public T findEqualUnique(String propertyName, Object value) throws ObjectNotFoundException {
        logger.log(Level.INFO, "finding unique T instance with property: " + propertyName
                + ", value: " + value);
        try {
            final String queryString = "select model from " + getPersistentClassName() + " model where model."
                    + propertyName + "= :propertyValue";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("propertyValue", value);

            return (T)query.getSingleResult();
        } catch (NoResultException re) {
            //logger.log(Level.SEVERE, "find by property name failed", re);
            throw new ObjectNotFoundException("Not found object " + propertyName + " with value " + value);
        }
    }

    public T findEqualUniqueCaseSensitive(String propertyName, Object value) throws ObjectNotFoundException {
        logger.log(Level.INFO, "finding unique T instance with property: " + propertyName
                + ", value: " + value);
        try {
            final String queryString = "select model from " + getPersistentClassName() + " model where LOWER(model."
                    + propertyName + " ) = LOWER(:propertyValue)";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("propertyValue", value);

            return (T)query.getSingleResult();
        } catch (NoResultException re) {
            //logger.log(Level.SEVERE, "find by property name failed", re);
            throw new ObjectNotFoundException("Not found object " + propertyName + " with value " + value);
        }
    }
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
	 *            number of results to return.
	 * @return List<T> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		logger.log(Level.INFO, "finding T instance with property: " + propertyName
				+ ", value: " + value);
		try {
			final String queryString = "select model from " + getPersistentClassName() + " model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			//logger.log(Level.SEVERE, "find by property name failed", re);
			throw re;
		}
	}

    public List<T> findProperty(String propertyName, final Object value) {
        logger.log(Level.INFO, "finding all T instance with property: " + propertyName
                + ", value: " + value);
        try {
            final String queryString = "select model from " + getPersistentClassName() + " model where model."
                    + propertyName + "= :propertyValue";
            Query query = entityManager.createQuery(queryString);
            query.setParameter("propertyValue", value);

            return query.getResultList();
        } catch (RuntimeException re) {
            //logger.log(Level.SEVERE, "find by property name failed", re);
            throw re;
        }
    }

	
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
	@SuppressWarnings("unchecked")
	public List<T> findAll(final int... rowStartIdxAndCount) {
		logger.log(Level.INFO, "finding all T instances " + getPersistentClassName());
		try {
			final String queryString = "select model from " + getPersistentClassName() + " model";
			Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			//logger.log(Level.SEVERE, "find all failed", re);
			throw re;
		}
	}
	
	public Object[] searchByProperties(Map<String, Object> properties,
			String sortExpression, String sortDirection, Integer offset,
			Integer limit) {
		try {
			final Object[] nameQuery = HibernateUtil.buildNameQuery(getPersistentClass(), properties, null, sortExpression,
                    sortDirection, true, false, null, true);
			
			String queryString = "select A " + nameQuery[0] + nameQuery[1];
            //System.out.println(queryString);
			Query query = entityManager.createQuery(queryString);
			if (nameQuery.length == 4) {
				String[] params = (String[]) nameQuery[2];
				Object[] values = (Object[]) nameQuery[3];
				for (int i = 0; i < params.length; i++) {

					query.setParameter(params[i], values[i]);
				}
			} 
			if (offset != null && offset >= 0) {
				query.setFirstResult(offset);
			}
			if (limit != null && limit > 0) {
				query.setMaxResults(limit);
			}
			List<T> res = query.getResultList();
			
			Object totalItem = 0;
			String queryTotal = "SELECT COUNT(*) " + nameQuery[0];
			
			Query query2 = entityManager.createQuery(queryTotal);
			if (nameQuery.length == 4) {
				String[] params = (String[]) nameQuery[2];
				Object[] values = (Object[]) nameQuery[3];
				for (int i = 0; i < params.length; i++) {

					query2.setParameter(params[i], values[i]);
				}
			} 
			totalItem = query2.getSingleResult();
			return new Object[]{totalItem, res};
		} catch (RuntimeException re) {
			//logger.log(Level.SEVERE, "find all failed", re);
		}
		return new Object[]{0, new ArrayList<T>()};
	}

    public Object[] searchByProperties(Map<String, Object> properties,
                                       String sortExpression, String sortDirection, Integer offset,
                                       Integer limit, String whereClause) {
        try {
            final Object[] nameQuery = HibernateUtil.buildNameQuery(getPersistentClass(), properties, null, sortExpression,
                    sortDirection, true, false, whereClause, true);

            String queryString = "select A " + nameQuery[0] + nameQuery[1];
            //System.out.println(queryString);
            Query query = entityManager.createQuery(queryString);
            if (nameQuery.length == 4) {
                String[] params = (String[]) nameQuery[2];
                Object[] values = (Object[]) nameQuery[3];
                for (int i = 0; i < params.length; i++) {

                    query.setParameter(params[i], values[i]);
                }
            }
            if (offset != null && offset >= 0) {
                query.setFirstResult(offset);
            }
            if (limit != null && limit > 0) {
                query.setMaxResults(limit);
            }
            List<T> res = query.getResultList();
            Object totalItem = 0;
            String queryTotal = "SELECT COUNT(*) " + nameQuery[0];

            Query query2 = entityManager.createQuery(queryTotal);
            if (nameQuery.length == 4) {
                String[] params = (String[]) nameQuery[2];
                Object[] values = (Object[]) nameQuery[3];
                for (int i = 0; i < params.length; i++) {

                    query2.setParameter(params[i], values[i]);
                }
            }
            totalItem = query2.getSingleResult();
            return new Object[]{totalItem, res};
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "find all failed", re);
        }
        return new Object[]{0, new ArrayList<T>()};
    }

    public List<T> findProperties(Map<String, Object> properties) {
        try {
            final Object[] nameQuery = HibernateUtil.buildNameQuery(getPersistentClass(), properties, null, null,
                    null, true, true, null, false);

            String queryString = "select A " + nameQuery[0] + nameQuery[1];
            //System.out.println(queryString);
            Query query = entityManager.createQuery(queryString);
            if (nameQuery.length == 4) {
                String[] params = (String[]) nameQuery[2];
                Object[] values = (Object[]) nameQuery[3];
                for (int i = 0; i < params.length; i++) {

                    query.setParameter(params[i], values[i]);
                }
            }

            List<T> res = query.getResultList();
            return  res;
        } catch (RuntimeException re) {
            logger.log(Level.SEVERE, "find all failed", re);
        }
        return new ArrayList<T>();
    }
	
	public Object findByProperties(Map<String, Object> properties,
			String sortExpression, String sortDirection, Integer offset,
			Integer limit) {
		try {
			final Object[] nameQuery = HibernateUtil.buildNameQuery(getPersistentClass(), properties, null, sortExpression,
					sortDirection, true, true, null, false);
			
			String queryString = "select A " + nameQuery[0] + nameQuery[1];
            //System.out.println(queryString);
			Query query = entityManager.createQuery(queryString);
			if (nameQuery.length == 4) {
				String[] params = (String[]) nameQuery[2];
				Object[] values = (Object[]) nameQuery[3];
				for (int i = 0; i < params.length; i++) {

					query.setParameter(params[i], values[i]);
				}
			} 
			if (offset != null && offset >= 0) {
				query.setFirstResult(offset);
			}
			if (limit != null && limit > 0) {
				query.setMaxResults(limit);
			}
			List<T> res = query.getResultList();
			return  res;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find all failed", re);
		}
		return new ArrayList<T>();
	}

	public Long countByProperties(Map<String, Object> properties) {
		Long totalItem = 0L;
		try {
			final Object[] nameQuery = HibernateUtil.buildNameQuery(getPersistentClass(), properties, null, null,
					null, true, false, null, true);
			String queryTotal = "SELECT COUNT(*) " + nameQuery[0] + nameQuery[1];
			Query query = entityManager.createQuery(queryTotal);
			if (nameQuery.length == 4) {
				String[] params = (String[]) nameQuery[2];
				Object[] values = (Object[]) nameQuery[3];
				for (int i = 0; i < params.length; i++) {

					query.setParameter(params[i], values[i]);
				}
			} 
			totalItem = (Long)query.getSingleResult();
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "count by properties failed", re);
		}
		return totalItem;
	}
	
	
}
