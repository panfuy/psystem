package ps.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ps.pojo.BaseAccount;
import ps.service.BaseAccountService;
import ps.until.FinalString;

/**
 * 账户处理Action
 * 
 * @author PanFu
 */
public class AccountAction {
	private Logger log = LoggerFactory.getLogger(AccountAction.class);
	private BaseAccountService accountService;
	private Boolean isLogin;
	private String sss;

	public String getSss() {
		return sss;
	}

	public void setSss(String sss) {
		this.sss = sss;
	}

	public void setAccountService(BaseAccountService accountService) {
		this.accountService = accountService;
	}

	@JSON(name = "isok")
	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * 登录验证
	 */
	public String login() {
		log.debug("用户登录");
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String userpass = request.getParameter("password");
		BaseAccount account = accountService
				.findByCheckUser(username, userpass);
		setIsLogin(false);
		if (account != null) {
			ServletActionContext.getContext().getSession().put(
					FinalString.LOGINUSER, account);
			setIsLogin(true);
			log.debug("登录成功，并记录登录用户返回TRUE");
		}
		return "success";
	}

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	public String exit() {
		ServletActionContext.getContext().getSession().remove(
				FinalString.LOGINUSER);
		return "global.login";
	}

	/**
	 * 账户设置
	 * 
	 * @return
	 */
	public String setting() {

		return "setting";
	}

}
