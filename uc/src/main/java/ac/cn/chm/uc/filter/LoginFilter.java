package ac.cn.chm.uc.filter;

import ac.cn.chm.api.login.LoginApi;
import ac.cn.chm.api.util.ApiConst;
import ac.cn.chm.base.PageData;
import ac.cn.chm.base.controller.BaseController;
import ac.cn.chm.consts.StringConst;
import ac.cn.chm.consts.UrlConst;
import ac.cn.chm.uc.interceptor.LoginInterceptor;
import ac.cn.chm.util.CheckUtil;
import ac.cn.chm.util.Tools;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Configuration
//@WebFilter(urlPatterns = "/user/*")

//获取配置文件
//@Component
//从配置文件中获取数据转换为对象
//@ConfigurationProperties(prefix = "access")
//@Order(1)//越小越先执行
//@WebFilter(filterName="loginFilter")
public class LoginFilter extends BaseController implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    private String sso;
    //@Value("$(access.key)")
    private String key = "key";
    //@Value("$(access.pass)")
    private String pass = "pass";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        log.info("自定义过滤器->doFilter");
//        filterChain.doFilter(servletRequest, servletResponse);
        LoginApi loginApi = new LoginApi(key, pass);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        PageData pd = this.getPageData();
        pd.put(StringConst.REDIS_ACC_TOKEN, CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACC_TOKEN))
                ? Tools.getCkValue(request, StringConst.REDIS_ACC_TOKEN) : pd.getString(StringConst.REDIS_ACC_TOKEN));

        if (CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACC_TOKEN))) {
            System.out.println("尚未登录，调到登录页面");
//            System.out.println(sso + UrlConst.PAGE_LOGIN);
//            loginApi.toLogin(request, response);
            //response.sendRedirect(sso + UrlConst.PAGE_LOGIN);
            return;
        }

        boolean isLogin = new LoginApi(key, pass).isLogin(pd);//调用该方法，若用户仍在线，会更新redis的在线时间
        //getSeesion(request);
        if (!isLogin) {
//            loginApi.toLogin(request, response);
            return;
        } else {
            HttpSession session = request.getSession();
            JSONObject userInfo = (JSONObject) session.getAttribute("userInfo");
            if (userInfo == null) {
                //已登录，但未保存基础用户信息
                //获取用户基础信息
                userInfo = loginApi.getLoginInfo(pd.getString(StringConst.REDIS_ACC_TOKEN));
                session.setAttribute("userInfo", userInfo);
            }
        }
        //添加这个才可以往下执行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}