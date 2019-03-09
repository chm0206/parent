package ac.cn.chm.base.service.impl;

import ac.cn.chm.base.service.BaseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> implements BaseService<T> {
    @Value("${mybatis-plus.global-config.db-config.logic-delete-value}")
    private String logicDeleteValue ;
    public Wrapper getWrapper(){
        return new QueryWrapper<>().ne("deleted",logicDeleteValue);
    }
}
