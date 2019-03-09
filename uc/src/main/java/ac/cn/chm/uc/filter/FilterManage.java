package ac.cn.chm.uc.filter;

import ac.cn.chm.base.filter.FilterInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Map;

//获取配置文件
@Component
//从配置文件中获取数据转换为对象
@ConfigurationProperties(prefix = "filter")//prefix获取指定的对象
@Configuration
public class FilterManage {
    private Map<String, FilterInfo> maps;

    public void setMaps(Map<String, FilterInfo> maps) {
        this.maps = maps;
    }

    /**
     * 对需要已认证用户才可以操作的权限进行过滤
     *
     * @return
     */
    //@Bean
    public FilterRegistrationBean loginFilter() {
        return getRegistrationBean(LoginFilter.class);
    }


    /**
     * 对需要已认证用户才可以操作的权限进行过滤
     *
     * @return
     */
//    @Bean
    public FilterRegistrationBean authFilter() {
        return getRegistrationBean(AuthFilter.class);
    }

    /**
     * 注册过滤器
     *
     * @param clazz 要注册的过滤器的实体类
     * @param <T>
     * @return
     */
    public <T extends Filter> FilterRegistrationBean getRegistrationBean(Class<T> clazz) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        String filterName = clazz.getSimpleName().toLowerCase().replace("filter", "");
        System.out.println(filterName);
        try {
            //注入过滤器
            registration.setFilter(clazz.newInstance());
            //拦截规则
            registration.addUrlPatterns(maps.get(filterName).getUrl());
//        registration.addUrlPatterns("/user/*","/user/*");
            //过滤器名称
            registration.setName(filterName + "Filter");
//        registration.setName(maps.get(filterName).getName());
            //过滤器顺序/级别
            registration.setOrder(maps.get(filterName).getOrder());
        } catch (InstantiationException e) {
            e.printStackTrace();
            registration = null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            registration = null;
        }
        return registration;
    }
}
