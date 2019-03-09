package ac.cn.chm.uc.user.service;

import ac.cn.chm.uc.user.model.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserInfoService extends IService<UserInfo> {
//    @Value("${mybatis-plus.global-config.db-config.logic-delete-value}")
//    String logicDeleteValue = null;
//
//    default Wrapper getWrapper(){
//        return new QueryWrapper<>().ne("deleted","D");
//    }
    IPage<UserInfo> listPage(Page<UserInfo> page,UserInfo userInfo);

}
