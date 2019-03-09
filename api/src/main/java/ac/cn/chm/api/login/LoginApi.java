package ac.cn.chm.api.login;

import ac.cn.chm.api.util.ApiConst;
import ac.cn.chm.api.util.HttpUtil;
import ac.cn.chm.base.PageData;
import ac.cn.chm.consts.CodeConst;
import ac.cn.chm.consts.ParamConst;
import ac.cn.chm.result.ResultInfo;
import ac.cn.chm.util.CheckUtil;
import com.alibaba.fastjson.JSONObject;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginApi {

    private String accessKey;
    private String accessPass;

    public LoginApi(String accessKey, String accessPass) {
        // 系统是否可用校验
        // 暂时不知道怎么写好
        this.accessKey = accessKey;
        this.accessPass = accessPass;
    }

    /**
     * 将accessKey、accessPass封装到pd中，便于访问时校验该系统是否有权限
     *
     * @param pd
     */
    private void addAccess(PageData pd) {
        pd.put("accesskey", this.accessKey);
        pd.put("accessPass", this.accessPass);
    }

    /**
     * 校验当前用户是否已登录
     *
     * @param pd
     * @return 用户已登录，返回true,用户未登录，返回false
     */
    public boolean isLogin(PageData pd) {
        // boolean isLogin = false;
        this.addAccess(pd);
        System.out.println(ApiConst.UC_CHECK_LOGIN);
        String result = null;//HttpUtil.doPost(ApiConst.UC_CHECK_LOGIN, pd);
        ResultInfo ri = JSONObject.parseObject(result, ResultInfo.class);
        if (ri != null && CodeConst.CODE_NORMAL.equals(ri.getCode())) {
            return true;
        } else {
            return false;
        }
        // return isLogin;
    }

    public static void main(String[] args) {
        PageData pd = new PageData();
        pd.put(ParamConst.FJ_ACC_TOKEN, "9C906DB81E74A08C");
		/*LoginApi la = new LoginApi("accesskey", "accessPass");
		boolean isLogin = la.isLogin(pd);
		System.out.println(isLogin);*/
        System.out.println(new LoginApi("accessKey", "accessPass").getLoginInfo("9C906DB81E74A08C"));
    }

    /**
     * 获取用户的基础信息
     *
     * @param accToken
     * @return 获取不到数据则返回null
     */
    public JSONObject getLoginInfo(String accToken) {
        // TODO Auto-generated method stub
        PageData pd = new PageData();
        pd.put(ParamConst.FJ_ACC_TOKEN, accToken);
        ResultInfo result = null;//JSONObject.parseObject(HttpUtil.doPost(ApiConst.UC_LOGIN_INFO, pd), ResultInfo.class);
        pd.clear();//清除无用的数据
        return result.getData().getJSONObject("userInfo");
    }

//    /**
//     * 单点登出（不确定是否可行）
//     *
//     * @param request
//     */
//    public void ssoLogout(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.removeAttribute("userInfo");
//    }

//    /**
//     * 跳转到SSO登录页面
//     *
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String value = request.getQueryString();    //获取参数
//        response.sendRedirect(ApiConst.UC_SSO_LOGIN + "?p=" + request.getRequestURL() + (CheckUtil.isEmpty(value) ? "" : "?" + value));//加上当前目录//request.getContextPath() + "/" +
//    }
}
