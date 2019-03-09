package ac.cn.chm.uc.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//import javax.activation.DataSource;

//@Configuration
//@MapperScan(basePackages = "ac.cn.chm.uc.mapper", sqlSessionTemplateRef = "readySqlSessionTemplate")
public class ReadyDataSourceConfig {
    @Bean(name = "readyDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ready")
    public DataSource setDataSource() {
        return (DataSource) new DruidDataSource();
    }

    @Bean(name = "readyTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("readyDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "readySqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("readyDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "readySqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("readySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}