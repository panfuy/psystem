package ps.dao;

import java.util.List;

import ps.pojo.BaseAccount;

public interface BaseAccountDAO {

	/**
	 * 保存
	 * @param transientInstance
	 */
	public void save(BaseAccount transientInstance);

	/**
	 * @param persistentInstance
	 */
	public void delete(BaseAccount persistentInstance);

	/**
	 * @param id
	 * @return
	 */
	public BaseAccount findById(java.lang.String id);

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findAll();

	/**
	 * @param detachedInstance
	 * @return
	 */
	public BaseAccount merge(BaseAccount detachedInstance);

	/**
	 * @param instance
	 */
	public void attachDirty(BaseAccount instance);

}