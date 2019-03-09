package ac.cn.chm.sso.service;

import ac.cn.chm.base.PageData;
import ac.cn.chm.sso.model.UserInfo;

public interface UserInfoService {//extends  IService<Long,UserInfo>
    /**
     * 获取用户的登录信息
     * @param pd 传递userID/userPhone/userCard和密码
     * @return
     */
    UserInfo findUserLoginInfo(PageData pd) throws Exception;
}