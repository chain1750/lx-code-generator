package com.lx.codegenerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author chenhaizhuang
 */
public class Main {

    public static void main(String[] args) throws IOException {
        generate(getProperties());
    }

    /**
     * 获取配置
     *
     * @return Properties
     * @throws IOException 异常
     */
    public static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream in = Main.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(in);
        return properties;
    }

    /**
     * 生成代码
     *
     * @param properties 参数
     */
    public static void generate(Properties properties) {
        String dbUrl = "jdbc:mysql://" + properties.getProperty("database.host")
                + ":" + properties.getProperty("database.port")
                + "/" + properties.getProperty("database.name")
                + "?useUnicode=true&characterEncoding=utf8&useSSL=false"
                + "&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";

        FastAutoGenerator.create(dbUrl,
                        properties.getProperty("database.username"), properties.getProperty("database.password"))
                .globalConfig(builder -> builder
                        .author(properties.getProperty("project.author"))
                        .outputDir("src/main/java"))
                .packageConfig(builder -> builder
                        .parent(properties.getProperty("project.module"))
                        .entity("entity")
                        .mapper("dao.mapper")
                        .service("dao")
                        .serviceImpl("dao.impl"))
                .templateConfig(builder -> builder
                        .entity("/templates/entity.java")
                        .mapper("/templates/mapper.java")
                        .service("/templates/service.java")
                        .serviceImpl("/templates/serviceImpl.java")
                        .disable(TemplateType.CONTROLLER, TemplateType.XML))
                .strategyConfig(builder -> builder
                        .addInclude(properties.getProperty("database.tables"))
                        .entityBuilder()
                        .enableTableFieldAnnotation()
                        .enableLombok()
                        .versionColumnName("version")
                        .logicDeleteColumnName("deleted")
                        .addTableFills(new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE))
                        .idType(IdType.AUTO)
                        .disableSerialVersionUID()
                        .fileOverride()
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .formatMapperFileName("%sMapper")
                        .fileOverride()
                        .serviceBuilder()
                        .formatServiceFileName("%sDAO")
                        .formatServiceImplFileName("%sDaoImpl")
                        .fileOverride())
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
