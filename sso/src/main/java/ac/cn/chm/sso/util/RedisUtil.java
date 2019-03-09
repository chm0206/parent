package ac.cn.chm.sso.util;

import ac.cn.chm.base.PageData;
import ac.cn.chm.consts.ParamConst;
import ac.cn.chm.consts.StringConst;
import ac.cn.chm.sso.model.UserInfo;
import ac.cn.chm.sso.util.redis.JedisCacheClient;
import ac.cn.chm.util.CheckUtil;
import ac.cn.chm.util.Tools;

public class RedisUtil {
	
	public static void createLoginInfo(PageData pd){
		
		String refreshToken = Tools.greantRefreshToken();
		String accToekn = null;
		try {
			accToekn = Tools.greantAccToken();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pd.put(accToekn, accToekn);
		pd.put(StringConst.REDIS_REFRESH_TOKEN, refreshToken);
		pd.put(StringConst.REDIS_USER_INFO, pd.get(StringConst.REDIS_USER_INFO));
	}
	
	/**
	 * redis用户登录操作
	 * @param pd
	 * @throws Exception
	 */
	public static void userLogin(JedisCacheClient jedis, PageData pd) throws Exception{
		UserInfo userInfo = (UserInfo) pd.get(StringConst.REDIS_USER_INFO);
		//U
		String accToken = pd.getString(StringConst.REDIS_ACC_TOKEN);
		//accToken_value:userID,ownerID,SSO_TERMAINAL(登录终端)
		String accTokenValue = userInfo.getUserId()+ ParamConst.DIV_KOMMA+userInfo.getOwnerId()+ParamConst.DIV_KOMMA+pd.getString(StringConst.SSO_TERMAINAL);
		//终端_key:userID+终端类型
		String terminal = userInfo.getUserId()+pd.getString(StringConst.SSO_TERMAINAL);//userID+用户登录终端
		//终端_value
		String terminalValue = accToken+ParamConst.DIV_KOMMA+"";//accToken+""推送地址，暂没有想好
		
		if(jedis.isExpire(terminal, 0)){//如果有，清除旧的登录信息
			String str = jedis.getV(terminal, 0);//str:accToken_key+推送地址等
			String[] redisList = str.split(ParamConst.DIV_KOMMA);
			if(redisList.length >0){
				jedis.delKey(redisList[0], 0);
			}
		}
		//设置过期时间
		//添加登录终端
		jedis.setVExpire(terminal, terminalValue, ParamConst.EXPIRE_30_MINUTE, 0);
		//添加accToken
		jedis.setVExpire(accToken, accTokenValue, ParamConst.EXPIRE_30_MINUTE, 0);
		//保存用户基础信息
		if(jedis.notExpire(userInfo.getUserId()+"", 0)){
			jedis.setVExpire(userInfo.getUserId()+"", userInfo, ParamConst.EXPIRE_30_MINUTE, 0);
		}
	}
	/**
	 * redis用户登出操作
	 * @param jedis
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static boolean userLogout(JedisCacheClient jedis, PageData pd) throws Exception{
		boolean result = false;
		if(CheckUtil.isEmpty(pd.getString("accToken"))){//如果为空，表示已退出
			return true;
		}
		String accToken = pd.getString("accToken");
		if(jedis.isExpire(accToken, 0)){//获取
			String str = jedis.getV(accToken, 0);//str:accToken_key+推送地址等
			String[] redisList = str.split(ParamConst.DIV_KOMMA);
			if(redisList.length >0){
				try{
				jedis.delKey(accToken, 0);//删除accToken
				jedis.delKey(redisList[0]+redisList[2], 0);//删除登录终端:userID+登录终端
				//通知其它系统
				}catch(Exception e){//异常表示登出操作失败
					e.printStackTrace();
					return false;
				}
			}
		}else{
			return true;//已退出
		}
		return result;
	}
}
