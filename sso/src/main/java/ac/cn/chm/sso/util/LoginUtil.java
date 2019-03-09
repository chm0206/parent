package ac.cn.chm.sso.util;

//import ac.cn.chm.api.util.HttpUtil;
import ac.cn.chm.sso.util.redis.JedisCacheClient;
import ac.cn.chm.base.PageData;
import ac.cn.chm.util.CheckUtil;

public class LoginUtil {
	/**
	 * 校验用户是否已登录
	 * @param pd
	 * @param jedis
	 * @return
	 * @throws Exception
	 */
	public static boolean isLogin(PageData pd, JedisCacheClient jedis)throws Exception{
		if (CheckUtil.isEmpty(pd.getString("accToken"))) {
			//System.out.println("尚未登录，调到登录页面");
			return false;
		}
		// accToken是否已过期
		boolean unlisted = jedis.notExpire(pd.getString("accToken"), 0);
		if(unlisted){
			//System.out.println("登录过期，调到登录页面");
			return false;
		}
		//System.out.println("已登录");
		return true;
	}
	
	public static void addLogin(){
		
	}
	/**
	 * 单点登出-通知其它系统退出(可能也要用消息，早点引进来吧)
	 * @throws Exception 
	 */
	public static void otherLogout() throws Exception{
		String sysUrl = "http://chm.cn:8081/mall/logout";
		//HttpUtil.doPost(sysUrl);//通知操作登出
	}
}
