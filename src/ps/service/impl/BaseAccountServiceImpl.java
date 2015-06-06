package ps.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ps.dao.BaseAccountDAO;
import ps.pojo.BaseAccount;
import ps.service.BaseAccountService;
import ps.until.UtilMD5;

public class BaseAccountServiceImpl implements BaseAccountService {
	private Logger log = LoggerFactory.getLogger(BaseAccountServiceImpl.class);
	private BaseAccountDAO accountDAO;

	public void setAccountDAO(BaseAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.service.BaseAccountService#findById(java.lang.String)
	 */
	public BaseAccount findById(String id) {
		return accountDAO.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps.service.BaseAccountService#findByCheckUser(java.lang.String,
	 * java.lang.String)
	 */
	public BaseAccount findByCheckUser(String user, String pwd) {
		BaseAccount ba = accountDAO.findById(user);
		log.debug("根据ID获取用户信息，获取内容是否为NULL? > " + (ba == null));
		if (ba != null) {
			boolean isvalid = false;
			try {
				isvalid = UtilMD5.validPassword(pwd, ba.getAccountPwd());
			} catch (Exception e) {
				log.error("验证用户密码时出错。", e);
			}
			if (isvalid) {
				ba.setAccountPwd(null);
				log.debug("用户与密码匹配验证成功，删除用户密码内容。");
			} else {
				ba = null;
			}
		}
		return ba;
	}

}
