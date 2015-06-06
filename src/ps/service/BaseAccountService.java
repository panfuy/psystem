package ps.service;

import ps.pojo.BaseAccount;

public interface BaseAccountService {
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public BaseAccount findById(String id);
	
	public BaseAccount findByCheckUser(String user,String pwd);
}
