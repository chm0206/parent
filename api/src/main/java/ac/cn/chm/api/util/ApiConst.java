package ac.cn.chm.api.util;

//import org.springframework.beans.factory.annotation.Value;

public class ApiConst {
	/**
	 * 用户中心基准路径
	 */
	public static final String UC_URL = "http://localhost:8080/";
	/**
	 * sso登录认证系统基准路径
	 */
	private static String SSO_URL = "http://localhost:8080/";
	/**
	 * 电商系统基准路径
	 */
	public static final String MALL_URL = "http://chm.cn:8081/mall/";
	/**
	 * 单点登录校验地址
	 */
	public static final String UC_CHECK_LOGIN = UC_URL + "sso/isLogin";
	/**
	 * 获取用户登录基准信息
	 */
	public static final String UC_LOGIN_INFO = UC_URL+"sso/getLoginInfo";
	/**
	 * 单点登录页面
	 */
	public static final String UC_SSO_LOGIN = SSO_URL+"sso/toLogin";
}
