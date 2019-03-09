package ac.cn.chm.sso.service.impl;

import ac.cn.chm.sso.mapper.UserInfoMapper;
import ac.cn.chm.sso.model.UserInfo;
import ac.cn.chm.sso.service.UserInfoService;
import ac.cn.chm.base.Page;
import ac.cn.chm.base.PageData;
import ac.cn.chm.consts.StringConst;
import ac.cn.chm.util.CheckUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
//
//    @Override
//    public int doCreate(UserInfo userInfo) throws Exception {
//        return this.userInfoMapper.doCreate(userInfo);
//    }
//
//    @Override
//    public int doUpdate(UserInfo userInfo) throws Exception {
//        PageData pd = new PageData();
//        pd.put("v", userInfo);
//        return this.userInfoMapper.doUpdate(pd);
//    }
//
//    @Override
//    public int doRemove(Long id) throws Exception {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserId(id);
//        return this.doUpdate(userInfo);
//    }
//
//    @Override
//    public int doRemoves(List<Long> ids) throws Exception {
//        int result = -1;
//        PageData pd = new PageData();
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserStatus(StringConst.STATUS_USER_DEL);
//        pd.put("ids", ids);
//        try {
//            result = this.userInfoMapper.doUpdate(pd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public List<UserInfo> listPagePd(Page page) throws Exception {
//        //PageHelper.startPage(pageNum, pageSize);
//        PageHelper.startPage(page.getCurrentPage(), page.getShowCount());
//        List<UserInfo> users = this.userInfoMapper.listPagePd(page);
//        return users;
//    }
//
//    @Override
//    public UserInfo findPdById(Long id) throws Exception {
//        return this.userInfoMapper.findPdById(id);
//    }
//
//    @Override
//    public List<UserInfo> findPdByIDs(List<Long> ids) throws Exception {
//        Page page = new Page();
//        PageData pd = new PageData();
//        pd.put("ids", ids);
//        page.setPd(pd);
//        return this.userInfoMapper.listPagePd(page);
//    }

    @Override
    public UserInfo findUserLoginInfo(PageData pd) throws Exception {
        UserInfo userInfo = new UserInfo();
        //if(CheckUtil.notEmptyAnd(pd.getString("userId"),pd.getString("userPass")))
        if (CheckUtil.notEmpty(pd.getStrings("userPass,userAccount")) ) {//&& CheckUtil.notEmptyOr(pd.getStrings("userId,userAccount"))
            userInfo.setUserAccount(pd.getString("userAccount"));
            userInfo.setUserId(Long.valueOf(pd.getString("userAccount")));
            userInfo.setUserPass(pd.getString("userPass"));
        } else {
            return null;
        }
        try {
            userInfo = this.userInfoMapper.findUserLoginInfo(userInfo);
        } catch (Exception e) {
            userInfo = null;
        }
        return userInfo;
    }
}
