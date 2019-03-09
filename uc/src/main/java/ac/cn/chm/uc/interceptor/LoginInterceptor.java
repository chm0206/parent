package ac.cn.chm.uc.interceptor;

import ac.cn.chm.util.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
//        log.info("---------------------开始进入请求地址拦截----------------------------");
//        HttpSession session = request.getSession();
//        if (!CheckUtil.isEmpty(session.getAttribute("userName"))) {
//            return true;
//        } else {
//            PrintWriter printWriter = response.getWriter();
//            printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");
//            return false;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        //log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        //log.info("---------------视图渲染之后的操作-------------------------0");
    }

}