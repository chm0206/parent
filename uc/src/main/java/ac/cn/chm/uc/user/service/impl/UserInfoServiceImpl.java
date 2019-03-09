package ac.cn.chm.uc.user.service.impl;

import ac.cn.chm.base.service.impl.BaseServiceImpl;
import ac.cn.chm.uc.user.mapper.UserInfoMapper;
import ac.cn.chm.uc.user.model.UserInfo;
import ac.cn.chm.uc.user.service.UserInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

@Service(value = "userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public IPage<UserInfo> listPage(Page<UserInfo> page, UserInfo userInfo) {
        return page.setRecords(this.baseMapper.listPage(page,new QueryWrapper<>().ne("deleted","D"), userInfo));
    }

}
