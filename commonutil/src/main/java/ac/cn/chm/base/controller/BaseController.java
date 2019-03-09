package ac.cn.chm.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ac.cn.chm.base.Jurisdiction;
import ac.cn.chm.base.PageData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	protected  Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 6357869213649815390L;
	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	/**
	 * 获取session内容
	 */
	public HttpSession getLocalSession() {
		HttpSession session = null ;
		try {
			 session = Jurisdiction.getSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session ;
	}

	/**
	 * 获取oOwner
	 */
	public String getOwner() {
		return null;
		// return ((UserInfo)
		// this.getLocalSession().getAttribute(ParamConst.SESSION_USER)).getOperatorId();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		// logger.info("");
		// logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		// logger.info("end");
		// logger.info("");
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @return
	 *//*
	public UserInfo getLoginUserInfo() {
		HttpSession session = null;
		try {
			session = Jurisdiction.getSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfo user = (UserInfo) session.getAttribute(ParamConst.SESSION_USER);
		return user;
	}
*/
	/**
	 * 获取用户登录IP
	 * 
	 * @return
	 * @date 2017年4月10日
	 */
	public String getLoginIP() {
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		return ip;
	}

	/**
	 * session存放
	 * 
	 * @param key
	 *            键
	 * @param obj
	 *            值
	 */
	public void setSession(String key, Object obj) {
		try {
			HttpSession session = Jurisdiction.getSession();
			session.setAttribute(key, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 去除pd对象中指定的空字符
	 * @param pd
	 * @param strName
	 */
	public void replaceBlank(PageData pd,String strName) {
		if("#".equals(pd.getString(strName))){
			pd.put(strName, "") ;
		}
	}
}