package ac.cn.chm.uc.user.controller;

import ac.cn.chm.base.Page;
import ac.cn.chm.base.PageData;
import ac.cn.chm.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "user")
public class UserModelController extends BaseController {

    @RequestMapping(value = "/toUserList")
    public ModelAndView toUserList(Page page) throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = this.getPageData();
        //List<UserInfo> users = this.userInfoService.listPagePd(page);
        //mv.addObject("users", users);
        mv.addObject("pd", pd);
        mv.setViewName("user/userList");
        return mv;
    }

    @RequestMapping(value = "/toUserInfo")
    public ModelAndView toUserInfo() throws Exception {
        ModelAndView mv = new ModelAndView();
        PageData pd = this.getPageData();
        //String userID = pd.getString("userID");
        Long userId = pd.getLong("userId");
        //UserInfo userInfo = this.userInfoService.findPdById(userId);
        //mv.addObject("userInfo", userInfo);
        mv.addObject("pd", pd);
        mv.setViewName("user/userList");
        return mv;
    }
}
