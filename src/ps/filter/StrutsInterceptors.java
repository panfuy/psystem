package ps.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import ps.until.FinalString;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class StrutsInterceptors extends AbstractInterceptor {
	private static final long serialVersionUID = 1123456456524345L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		//String path = request.getContextPath();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		//如果是登录请求，直接执行
		if ("account_login".contains(action)) {
			return arg0.invoke();
		} else {
			//验证是否登录
			if (session.getAttribute(FinalString.LOGINUSER) != null) {
				return arg0.invoke();		
			}
			return "global.login";
		}
	}

}
