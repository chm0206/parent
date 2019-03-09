package ac.cn.chm.sso.mapper;

import ac.cn.chm.sso.model.UserInfo;
import ac.cn.chm.base.dao.IDAO;

/**
 * 用户操作类持久层
 */
public interface UserInfoMapper extends IDAO<Long, UserInfo> {
    /**
     * 获取用户的登录信息
     * @param userInfo 用户信息
     * @return
     * @throws Exception
     */
    UserInfo findUserLoginInfo(UserInfo userInfo)throws Exception;

}