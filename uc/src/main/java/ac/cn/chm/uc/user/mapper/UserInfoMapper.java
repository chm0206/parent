package ac.cn.chm.uc.user.mapper;

import ac.cn.chm.uc.user.model.UserInfo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

//    @Select("testSelectId")
//    @Select("SELECT * from uc_user_info")
//    IPage<UserInfo> testSelectId(IPage page, @Param("userInfo")UserInfo userInfo);

//    @Select("${ew.customSqlSegment}")
    List<UserInfo> listPage(IPage page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param(value = "userInfo") UserInfo userInfo);

}
