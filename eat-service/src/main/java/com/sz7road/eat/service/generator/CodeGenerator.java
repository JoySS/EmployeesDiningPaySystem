package com.sz7road.eat.service.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author Panda.Z
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("E://dream");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList

        gc.setAuthor("Panda.Zh");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("pg_user");
        dsc.setPassword("qqdba_changic@123");
        dsc.setUrl("jdbc:mysql://10.10.7.103:2433/db_gk_0001?characterEncoding=utf8");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
//        strategy.setTablePrefix("t_d_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{
                "t_biz_job",
                "t_biz_job_major",
                "t_biz_majors",
                "t_biz_score_line",
                "t_biz_university",
                "t_biz_university_admission",
                "t_biz_university_introduction",
                "t_biz_university_majors",
                "t_biz_university_majors_score_line",
                "t_biz_university_score_line",
                "t_biz_user",
                "t_biz_user_extra",
                "t_biz_user_third",
                "t_biz_vip",

                "t_c_degree_type",
                "t_c_level_type",
                "t_c_provinces",
                "t_c_subject_type"

        }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        strategy.setFieldNaming(NamingStrategy.underline_to_camel);
        // 自定义实体父类
        strategy.setSuperEntityClass("IBaseModel");
        // 自定义实体，公共字段
        strategy.setSuperEntityColumns(new String[]{"id", "enable", "create_by", "create_time", "update_by", "update_time"});
//         自定义 mapper 父类
        strategy.setSuperMapperClass("IBaseMapper");
        // 自定义 service 父类
        strategy.setSuperServiceClass("IBaseService");
        // 自定义 service 实现类父类
//        strategy.setSuperServiceImplClass("org.ibase4j.core.base.BaseService");
        // 自定义 controller 父类
//        strategy.setSuperControllerClass("org.ibase4j.core.base.BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        mpg.setStrategy(strategy);
        // 包配置
        String module = ".biz";
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.vgkzy");
        pc.setEntity("api.entity.biz");
        pc.setMapper("service.mapper");
        pc.setXml("service.mapper.xml");
        pc.setService("api.service");
        pc.setServiceImpl("service.impl");
        pc.setController("web.controller");
        mpg.setPackageInfo(pc);
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
        // 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("template/entity.java.vm");
        tc.setMapper("template/mapper.java.vm");
        tc.setXml("template/mapper.xml.vm");
        tc.setService("template/service.java.vm");
        tc.setServiceImpl("template/serviceImpl.java.vm");
        tc.setController("template/controller.java.vm");
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();

    }
}
