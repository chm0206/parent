package ac.cn.chm.uc.user.controller;

import ac.cn.chm.base.PageData;
import ac.cn.chm.base.controller.BaseController;
import ac.cn.chm.consts.StringConst;
import ac.cn.chm.result.ResultInfo;
import ac.cn.chm.uc.user.model.UserInfo;
import ac.cn.chm.uc.user.service.UserInfoService;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * 用户信息
 *
 * @author Administrator
 * @data 2018/04/26 23:01
 */
@Controller
@RequestMapping(value = "users")
public class UserController extends BaseController {


    @Resource(name = "userInfoService")
//    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取分页数据
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{current}/{size}",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo listUserInfo(@PathVariable("current") Long current,@PathVariable("size") Long size) throws Exception {
        ResultInfo result = new ResultInfo();
        UserInfo user = new UserInfo();
        user.setUserPass("123");
        Page<UserInfo> page = new Page<>(current,size);
        List<Object> list1 = this.userInfoService.listObjs();
        IPage<UserInfo> list = this.userInfoService.listPage(page,user);
        result.put("list",list);
        return result;
    }

    /**
     * 获取指定数据
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/{userId}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo getUserInfo(@PathVariable("userId") Long userId) throws Exception {
        ResultInfo result = new ResultInfo();
        PageData pd = this.getPageData();
        result.put("pd", pd);
        return result;
    }

    /**
     * 新增数据
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo saveUserInfo(UserInfo user) throws Exception {
        ResultInfo result = new ResultInfo();
//        user.setUserId(123L + new Random().nextInt(1000));
        user.setUserAccount("CHM" + user.getUserId());
        user.setUserPass("123");
        user.setUserStatus(StringConst.STATUS_USER_DEL);
        //user.setAddTime(new Date());
        PageData pd = this.getPageData();
        if (this.userInfoService.save(user)) {
            result.error();
        }
        result.put("pd", pd);
        return result;
    }

    /**
     * 全部更新
     *
     * @return
     * @throws Exception
     */
    @PutMapping(value = "/", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo editAllUserInfo(UserInfo user) throws Exception {
        ResultInfo result = new ResultInfo();
        //JSONObject object = new JSONObject();
        PageData pd = this.getPageData();
        user.setUserAccount("chm" + 1);
        user.setUserId(123L);
//        if (this.userInfoService.doUpdate(user) <= 0) {
//            result.error();
//        }
        return result;
    }

    /**
     * 部分更新
     *
     * @return
     * @throws Exception
     */
    @PatchMapping(value = "/",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo editUserInfo(UserInfo user) throws Exception {
        ResultInfo result = new ResultInfo();
        //JSONObject object = new JSONObject();
        PageData pd = this.getPageData();
//        if (CheckUtil.isEmpty(user) || this.userInfoService.doUpdate(user) <= 0) {
//            result.error();
//        }
        return result;
    }

    /**
     * 删除用户
     *
     * @return
     * @throws Exception
     */
    @DeleteMapping(value = "/{userIds}",  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo removeUserInfo(@PathVariable("userIds") String userIds) throws Exception {
        ResultInfo result = new ResultInfo();
        //JSONObject object = new JSONObject();
        PageData pd = this.getPageData();
//        if (this.userInfoService.doRemoves(FormatUtil.str2List(userIds, Long.class)) <= 0) {
//            result.error();
//        }
        return result;
    }
    @PostMapping(value = "/test", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultInfo test(@RequestBody List<UserInfo> users) throws Exception{
        ResultInfo result = new ResultInfo();
        return result;
    }
@RequestMapping(value="test1")
    public ModelAndView test1() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/userList");
        return mv;
    }
}
