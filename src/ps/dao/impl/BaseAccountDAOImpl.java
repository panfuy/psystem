package ps.dao.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ps.dao.BaseAccountDAO;
import ps.pojo.BaseAccount;

/**
 * @see ps.pojo.BaseAccount
 * @author MyEclipse Persistence Tools
 */

public class BaseAccountDAOImpl extends HibernateDaoSupport implements
		BaseAccountDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BaseAccountDAOImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.dao.BaseAccountDAO#save(ps.pojo.BaseAccount)
	 */
	public void save(BaseAccount transientInstance) {
		log.debug("saving BaseAccount instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.dao.BaseAccountDAO#delete(ps.pojo.BaseAccount)
	 */
	public void delete(BaseAccount persistentInstance) {
		log.debug("deleting BaseAccount instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.dao.BaseAccountDAO#findById(java.lang.String)
	 */
	public BaseAccount findById(java.lang.String id) {
		log.debug("getting BaseAccount instance with id: " + id);
		try {
			BaseAccount instance = (BaseAccount) getHibernateTemplate().get(
					"ps.pojo.BaseAccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("根据ID获取Account数据失败。", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.dao.BaseAccountDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all BaseAccount instances");
		try {
			String queryString = "from BaseAccount";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.dao.BaseAccountDAO#merge(ps.pojo.BaseAccount)
	 */
	public BaseAccount merge(BaseAccount detachedInstance) {
		log.debug("merging BaseAccount instance");
		try {
			BaseAccount result = (BaseAccount) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.dao.BaseAccountDAO#attachDirty(ps.pojo.BaseAccount)
	 */
	public void attachDirty(BaseAccount instance) {
		log.debug("attaching dirty BaseAccount instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}