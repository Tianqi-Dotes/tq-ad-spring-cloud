/*
package com.tq.ad;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    */
/**
     * <p>
     * 读取控制台内容
     * </p>
     *//*

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {

        List<String> tableNames = new ArrayList<>(Arrays.asList(
                "creative_unit"
//                "nx_role_permission",
//                "nx_role",
//                "nx_permission",
//                "nx_branch_office",
//                "nx_head_office",
//                "nx_approval_category",
//                "nx_approval_process",
//                "nx_approval_process_step",
//                "nx_approval_progress",
//                "nx_promotion"
        ));
        List<String> moduleNames = new ArrayList<>(Arrays.asList(
                "creative_unit"
//                "role_permission",
//                "role",
//                "permission",
//                "branch_office",
//                "head_office",
//                "approval_category",
//                "approval_process",
//                "approval_process_step",
//                "approval_progress",
//                "promotion"
        ));

        for (int i = 0; i < moduleNames.size(); i++) {
            generateCodeByTable(tableNames.get(i), moduleNames.get(i));
        }
    }

    private static void generateCodeByTable(String tableName, String moduleName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/tq-ad-service/ad-sponsor/src/main/java");
        gc.setAuthor("tq");
        gc.setOpen(false);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/ad?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8&useAffectedRows=true");
//        dsc.setUrl("jdbc:mysql://localhost:3306/goodtruck?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("tq1994");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tq.ad");
        pc.setEntity("entity.unit_condition");
        pc.setService("service");
        pc.setController("controller");
        pc.setServiceImpl("service.serviceImp");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//         如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/xmlmappers/"  + tableInfo.getEntityName() + "Mapper" + ".xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.entityTableFieldAnnotationEnable(true);
        strategy.setEntityBuilderModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        自定义实体父类
//        strategy.setSuperEntityClass("com.nuoxin.goodtruckbackend.model.entity");
//        自定义实体，公共字段
//        strategy.setSuperEntityColumns("contractid");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
//        自定义 controller 父类
//        strategy.setSuperControllerClass("com.nuoxin.goodtruckbackend.controller");
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperEntityClass(null);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
*/
