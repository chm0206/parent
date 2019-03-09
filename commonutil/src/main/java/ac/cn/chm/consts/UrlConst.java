package ac.cn.chm.consts;

/**
 * 跳转地址常量类
 * 
 * @author chm
 *
 */
public class UrlConst {
	/**
	 * 访问地址后缀
	 */
	public static final String PAGE_SUFFIX = "";//.do
	/**
	 * 登录页面
	 */
	public static final String PAGE_LOGIN = "sso/toLogin" + PAGE_SUFFIX;
	/**
	 * 登录账号方法
	 */
	public static final String ACTION_LOGIN = "sso/login" + PAGE_SUFFIX;
	/**
	 * 注册页面
	 */
	public static final String PAGE_REGISTER = "sso/toRegister" + PAGE_SUFFIX;// register
	/**
	 * 主页面
	 */
	//public static final String PAGE_INDEX = "login/index" + PAGE_SUFFIX;
	public static final String PAGE_INDEX = "user/toUserList" + PAGE_SUFFIX;
}
