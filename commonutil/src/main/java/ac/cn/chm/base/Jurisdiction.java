package ac.cn.chm.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;





/**
 * 权限处理
 * @author riseinfo.cn
 * 修改日期：2015/11/19
 */
public class Jurisdiction {

    /**shiro管理的session
     * @return
     */
	/*public static Session getSession(){
		//Subject currentUser = SecurityUtils.getSubject();
		Session se = SecurityUtils.getSubject().getSession();
		if(!"".equals(se) && !se.equals(null))
			return se;
		else
			return null;
	}*/
    public static HttpSession getSession() throws Exception {
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession sess = myc.getSession();

        try {
            if(!"".equals(sess) && !sess.equals(null))
                return sess;
            else
                return null;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取session中的用户登录信息
     * @return
     * @throws Exception
     *//*
	public static UserInfoView getLoginUserInfo() throws Exception {
		UserInfoView uiv = new UserInfoView() ;

		return uiv ;

	}*/

    /**
     * 获取归属ID oOwner
     * @return
     * @throws Exception
     *//*
	public static String getoOwner() throws Exception {
		String oOwner = "";
		if(ParamConst.isSession){ //如果当前系统存储方式是session
			oOwner = String.valueOf( getSession().getAttribute("oOwner") );
		}else{
			oOwner = null ;
		}
		return oOwner ;
	}*/

    /**
     * 获取当前登录用户信息
     * @param pd
     * @return
     * @throws Exception
     *//*
	public static UserInfoView getLoginUserInfo(PageData pd) throws Exception {
		UserInfoView uiv = new UserInfoView() ;

		return uiv ;
	}*/

    /**
     * 获取归属ID oOwner
     * @return
     * @throws Exception
     */
    public static String getoOwner(PageData pd) throws Exception {
        String oOwner = "";

        return oOwner ;
    }

    /**
     * 获取当前请求中的cookie[]
     * @param pd
     * @return
     * @throws Exception
     */
    public static Cookie[] getRequesCookies(PageData pd) throws Exception {
        HttpServletRequest req = pd.getRequest() ;
        if(req != null){
            Cookie[] allCookies = req.getCookies();
            return allCookies ;
        }else{
            return null ;
        }
    }

    /**
     * 获取当前请求中的cookie指定键的值
     * @param pd
     * @return
     * @throws Exception
     */
    public static String getCookieID(PageData pd,String keyName) throws Exception {
        String result = null ;
        Cookie[] rqc = getRequesCookies(pd) ;
        for(Cookie ck : rqc){
            if(keyName.equals(ck.getName())){
                result = ck.getValue() ;
            }
        }
        return result ;

    }

    /**
     * 获取用户ID
     * @param pd
     * @return
     * @throws Exception
     *//*
	public static String getUserID(PageData pd) throws Exception {
		String userID = pd.getString(ParamConst.UC_USER_ID) ;
		if(Tools.isEmpty(userID)){
			//从Cookie中获取accToken
			String accToken = getCookieID(pd,ParamConst.UC_ACC_TOKEN) ;
			//通过accToken获取登录用户ID
			if(Tools.notEmpty(accToken)){
				userID = RedisUtil.getUserIDbyAccToken(accToken) ;
			}else{
				userID = null ;
			}
		}
		return userID ;
	}*/
}
