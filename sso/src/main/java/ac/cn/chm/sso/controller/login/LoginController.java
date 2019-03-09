package ac.cn.chm.sso.controller.login;

import ac.cn.chm.api.util.HttpUtil;
import ac.cn.chm.base.PageData;
import ac.cn.chm.base.controller.BaseController;
import ac.cn.chm.consts.CodeConst;
import ac.cn.chm.consts.ParamConst;
import ac.cn.chm.consts.StringConst;
import ac.cn.chm.consts.UrlConst;
import ac.cn.chm.result.ResultInfo;
import ac.cn.chm.sso.model.UserInfo;
import ac.cn.chm.sso.service.UserInfoService;
import ac.cn.chm.sso.util.LoginUtil;
import ac.cn.chm.sso.util.RedisUtil;
import ac.cn.chm.sso.util.redis.JedisCacheClient;
import ac.cn.chm.util.CheckUtil;
import ac.cn.chm.util.Tools;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

//import redis.clients.jedis.JedisPool;

/**
 * 登录操作
 */
//import CodeConst;
@RestController
@RequestMapping(value="sso")
public class LoginController extends BaseController {

    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;
    @Autowired
    private JedisCacheClient jedis;

    @RequestMapping("/abc")
    public String wev(){
        return "index";
    }

    @RequestMapping(value = "toLogin")
    public ModelAndView toLogin() throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = this.getPageData();
        mv.setViewName("login/login1");
        mv.addObject("pd",pd);
        mv.addObject("loginUrl", UrlConst.ACTION_LOGIN);
        mv.addObject("toRegister", UrlConst.PAGE_REGISTER);
        return mv;
    }

    @RequestMapping(value = "index")
    public ModelAndView toIndex() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/index");
        return mv;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultInfo login() throws Exception {
        ResultInfo result = new ResultInfo();
        PageData pd = this.getPageData();
        UserInfo userInfo = this.userInfoService.findUserLoginInfo(pd);
        if (userInfo == null) {// 找不到此用户或用户密码错误
            //return new ResultInfo(CodeConst.USER_OR_PASS_ERROR, "用户账号或密码错误");
            result.error(CodeConst.USER_OR_PASS_ERROR, "用户账号或密码错误");
        } else {// 登录成功,保存用户信息、生成accToken
            if(CheckUtil.isEmpty(pd.getString(StringConst.SSO_TERMAINAL))){//判断终端是否为空
                pd.put(StringConst.SSO_TERMAINAL, StringConst.SSO_TERMAINAL_COMPUTER);//默认为电脑
            }
            /**
             * 序号	说明			key				value
             * 1	accToken	时间戳转换码		userID,ownerID
             * 2	用户信息		userInfo		用户信息
             * 3	登录终端码	userInfo+term	accToken+推送地址
             */
            //accToken-key、value
            String accToken = Tools.greantAccToken();
            pd.put(StringConst.REDIS_ACC_TOKEN, accToken);
            pd.put(StringConst.REDIS_USER_INFO, userInfo);
            RedisUtil.userLogin(jedis,pd);
            result.put(StringConst.REDIS_ACC_TOKEN,accToken);
            if(ParamConst.GET_USERINFO.equals(pd.get(ParamConst.GET_INFO))){
                result.put(StringConst.REDIS_USER_INFO,userInfo);
            }
            result.put(StringConst.REDIRECT_URL,  CheckUtil.notEmpty(pd.getString("p"))?pd.getString("p"):UrlConst.PAGE_INDEX);// 登录成功跳转到登录页面
        }
        return result;
    }
    /**
     * 校验指定的用户是否已登录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/isLogin")
    @ResponseBody
    public ResultInfo isLogin() throws Exception {
        ResultInfo result = new ResultInfo();
        PageData pd = this.getPageData();
        //获取accToken（从pd或是从cookies里获取）
        String accToken = CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACC_TOKEN))
                ? Tools.getCkValue(pd.getRequest(), StringConst.REDIS_ACC_TOKEN):pd.getString(StringConst.REDIS_ACC_TOKEN);
        if(CheckUtil.isEmpty(accToken)){
            result.setCode(CodeConst.CODE_NOT_LOGIN);
        }else{
            pd.put("accToken", accToken);
            if (LoginUtil.isLogin(pd, jedis)) {
                return result;
            }
            result.setCode(CodeConst.CODE_NOT_LOGIN);
        }
        return result;
    }
    /**
     * 获取用户登录基础信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLoginInfo")
    @ResponseBody
    public ResultInfo getLoginInfo() throws Exception {
        ResultInfo result = new ResultInfo();
        PageData pd = this.getPageData();
        //获取accToken（从pd或是从cookies里获取）
        String accToken = CheckUtil.isEmpty(pd.getString(StringConst.REDIS_ACC_TOKEN))
                ? Tools.getCkValue(pd.getRequest(), StringConst.REDIS_ACC_TOKEN):pd.getString(StringConst.REDIS_ACC_TOKEN);
        if(CheckUtil.isEmpty(accToken)){//accToken为空，则表示没有登录
            result.setCode(CodeConst.CODE_NOT_LOGIN);
        }else{//获取用户登录信息
            pd.put("accToken", accToken);
            try{
                String accTokenValue = jedis.getV(accToken, 0);//获取accToken的值
                if(CheckUtil.isEmpty(accTokenValue)){
                    result.setCode(CodeConst.CODE_NOT_LOGIN);
                }else{
                    String[] user = accTokenValue.split(ParamConst.DIV_KOMMA);
                    JSONObject userInfo = jedis.getV(user[0], 0);
                    result.put("userInfo",userInfo);
                }
            }catch(Exception e){
                e.printStackTrace();
                result.setCode(CodeConst.CODE_NOT_LOGIN);
            }
        }
        return result;
    }
    /**
     * sso单点登出
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResultInfo logout() throws Exception {
        ResultInfo result = new ResultInfo();
        PageData pd = this.getPageData();
        if(RedisUtil.userLogout(jedis, pd)){//登出失败
            result.put("pd",pd);
            //通知其它的系统要作退出操作
            ssoLogout(pd);
        }else{
            result.setCode(CodeConst.CODE_FAIL);
        }
        return result;
    }
    /**
     * 子系统单点登出操作方法
     * @param pd
     */
    private void ssoLogout(PageData pd){
        //HttpServletRequest request = pd.getRequest();
//        HttpUtil.doPost("http://chm.cn:8081/mall/sso/logout",pd);
        System.out.println(1234);
    }
}
