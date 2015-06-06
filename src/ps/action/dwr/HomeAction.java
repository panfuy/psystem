package ps.action.dwr;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeAction {

	/**
	 * 获取服务器时间
	 * @return
	 */
	public String getServerTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
}
