package ac.cn.chm.uc.utils.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * <p>
 * 代码生成器演示
 * </p>
 *
 * @author hubin
 * @since 2016-12-01
 */
public class MysqlGenerator {

    public static String JDBC_URL = "jdbc:mysql://localhost:3306/uc?characterEncoding=utf8";
    public static String JDBC_USER_NAME = "root";
    public static String JDBC_PASSWORD = "root";
    public static String JDBC_DRIVER_NAME= "com.mysql.jdbc.Driver";

    public static String BASE_URL = "E:/public/workspace/parent/";

    public static String PRODECT_URL = "uc";

    public static String ADD_URL =BASE_URL+PRODECT_URL+"/";

    public static String MAPPER_URL = "mapper/";

    public static String[] TABLE_PREFIX = {"uc_", "test_","t_"};

    public static String DIV_PACKAGEING = "ac.cn.chm.uc";
    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        int result = 1;
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
            // 全局配置
            new GlobalConfig()
                .setOutputDir(ADD_URL+"src/main/java")//输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                //.setKotlin(true) 是否生成 kotlin 代码
                .setAuthor("陈焕明")
            // 自定义文件命名，注意 %s 会自动填充表实体属性！
            // .setEntityName("%sEntity");
            // .setMapperName("%sDao")
            // .setXmlName("%sDao")
            // .setServiceName("MP%sService")
            // .setServiceImplName("%sServiceDiy")
            // .setControllerName("%sAction")
        ).setDataSource(
            // 数据源配置
            new DataSourceConfig()
                .setDbType(DbType.MYSQL)// 数据库类型
                //.setTypeConvert(new MySqlTypeConvert() {
                //    // 自定义数据库表字段类型转换【可选】
                //    @Override
                //    public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                //        System.out.println("转换类型：" + fieldType);
                //        // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                //        //    return DbColumnType.BOOLEAN;
                //        // }
                //        return super.processTypeConvert(globalConfig, fieldType);
                //    }
                //})
                .setDriverName(JDBC_DRIVER_NAME)
                .setUsername(JDBC_USER_NAME)
                .setPassword(JDBC_PASSWORD)
                .setUrl(JDBC_URL)
        ).setStrategy(
            // 策略配置
            new StrategyConfig()
                // .setCapitalMode(true)// 全局大写命名
                // .setDbColumnUnderline(true)//全局下划线命名
                .setTablePrefix(TABLE_PREFIX)// 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                // .setInclude(new String[] { "user" }) // 需要生成的表
                // .setExclude(new String[]{"test"}) // 排除生成的表
                // 自定义实体父类
                // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                // 自定义实体，公共字段
                //.setSuperEntityColumns(new String[]{"test_id"})
                .setTableFillList(tableFillList)
            // 自定义 mapper 父类
            // .setSuperMapperClass("com.baomidou.demo.TestMapper")
            // 自定义 service 父类
            // .setSuperServiceClass("com.baomidou.demo.TestService")
            // 自定义 service 实现类父类
            // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
            // 自定义 controller 父类
            // .setSuperControllerClass("com.baomidou.demo.TestController")
            // 【实体】是否生成字段常量（默认 false）
            // public static final String ID = "test_id";
            // .setEntityColumnConstant(true)
            // 【实体】是否为构建者模型（默认 false）
            // public User setName(String name) {this.name = name; return this;}
            // .setEntityBuilderModel(true)
            // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
            // .setEntityLombokModel(true)
            // Boolean类型字段是否移除is前缀处理
            // .setEntityBooleanColumnRemoveIsPrefix(true)
            // .setRestControllerStyle(true)
            // .setControllerMappingHyphenStyle(true)
        ).setPackageInfo(
            // 包配置
            new PackageConfig()
                //.setModuleName("GroupInfo")
                .setParent(DIV_PACKAGEING)// 自定义包路径
                .setController("controller")// 这里是控制器包名，默认 web
        ).setCfg(
            // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
            new InjectionConfig() {
                @Override
                public void initMap() {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                    this.setMap(map);
                }
            }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                "/templates/mapper.xml" + ((1 == result) ? ".ftl" : ".vm")) {
                // 自定义输出文件目录
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return ADD_URL+"src/main/resources/"+MAPPER_URL + tableInfo.getEntityName() + ".xml";
                }
            }))
        ).setTemplate(
            // 关闭默认 xml 生成，调整生成 至 根目录
            new TemplateConfig().setXml(null)
            // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
            // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
            // .setController("...");
            // .setEntity("...");
            // .setMapper("...");
            // .setXml("...");
            // .setService("...");
            // .setServiceImpl("...");
        );
        // 执行生成
        if (1 == result) {
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        }
        mpg.execute();

        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}