package ac.cn.chm.uc.config;

//import ac.cn.chm.uc.dynamic.DatabasePlugin;
//import ac.cn.chm.uc.dynamic.DatabaseType;
//import ac.cn.chm.uc.dynamic.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.apache.ibatis.plugin.Interceptor;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@EnableTransactionManagement//事务支持
@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件（不使用该插件，分页查询会先获取所有数据，再取得分页数据）
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     * SQL执行效率插件
     */
    @Bean
//  @Profile({ "dev", "test" }) // 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

//
//
//    @Autowired
//    DruidProperties druidProperties;
//
//    @Bean(name = "masterDataSource")
//    @Qualifier("masterDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "slaveDataSource")
//    @Qualifier("slaveDataSource")
//    public DataSource slaveDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        druidProperties.coinfig(dataSource);
//
//        return dataSource;
//    }
//
//    /**
//     *  构造多数据源连接池
//     *  Master 数据源连接池采用 HikariDataSource
//     *  Slave  数据源连接池采用 DruidDataSource
//     * @param master
//     * @param slave
//     * @return
//     */
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource master,
//                                        @Qualifier("slaveDataSource") DataSource slave) {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DatabaseType.master, master);
//        targetDataSources.put(DatabaseType.slave, slave);
//
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(slave);// 默认的datasource设置为myTestDbDataSourcereturn dataSource;
//        return dataSource;
//    }
//
//    @Bean
//    public MybatisSqlSessionFactoryBean sqlSessionFactory(@Qualifier("masterDataSource") DataSource master,
//                                                          @Qualifier("slaveDataSource") DataSource slave) throws Exception {
//        MybatisSqlSessionFactoryBean fb = new MybatisSqlSessionFactoryBean();
//        fb.setDataSource(this.dataSource(master, slave));
//        // 是否启动多数据源配置，目的是方便多环境下在本地环境调试，不影响其他环境
//        if (druidProperties.getOnOff() == true) {
//            fb.setPlugins(new Interceptor[]{new DatabasePlugin()});
//        }
//        return fb;
//    }
}