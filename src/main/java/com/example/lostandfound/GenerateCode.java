package com.example.lostandfound;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class GenerateCode {

    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://150.158.4.148:10078/LostandFound?serverTimezone=Asia/Shanghai", "root", "123456")
            .build();

    public static void main(String[] args) {


        FastAutoGenerator.create("jdbc:mysql://150.158.4.148:10078/LostandFound?serverTimezone=Asia/Shanghai","root","123456")
                .globalConfig(builder -> {
                    builder.author("ilpvc")
                            .commentDate("yyyy-MM-dd hh:mm:ss")
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.example")
                            .moduleName("lostandfound")
                            .entity("entity")
                            .service("service")
                            .controller("controller")
                            .serviceImpl("service.impl")
                            .mapper("com/example/lostandfound/mapper")
                            .xml("com/example/lostandfound/mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"));
                })
                .strategyConfig(builder -> {
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("is_deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .enableHyphenStyle()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
