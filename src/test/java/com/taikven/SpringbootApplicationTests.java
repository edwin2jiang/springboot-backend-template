package com.taikven;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.taikven.entity.Hotel;
import com.taikven.entity.Photo;
import com.taikven.service.PhotoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    PhotoService service;



//    @Autowired
//    private BookService bookService;
//
//    @Autowired
//    private BookMapper bookDao;

    /****
     * 代码生成器
     */

//    @Test
//    public void  generate(){
//            //创建一个代码生成器
//            FastAutoGenerator.create("jdbc:mysql://47.115.228.244:3306/home_stay",
//                            "root", "1c1c812c58fdf041")
//                    //全局配置(GlobalConfig)
//                    .globalConfig(builder -> {
//                        builder.author("") // 设置作者，可以写自己名字
//                                //.enableSwagger() // 开启 swagger 模式，这个是接口文档生成器，如果开启的话，就还需要导入swagger依赖
//                                .fileOverride() // 覆盖已生成文件
//                                .dateType(DateType.TIME_PACK) //时间策略
//                                .commentDate("yyyy-MM-dd") //注释日期
//                                .outputDir("D:\\home-stay-backend\\src\\main\\java"); // 指定输出目录，一般指定到java目录
//                    })
//                    //包配置(PackageConfig)
//                    .packageConfig(builder -> {
//                        builder.parent("com.taikven") // 设置父包名
//                                .moduleName("") // 设置父包模块名，这里一般不设置
//                                .pathInfo(Collections.singletonMap(OutputFile.mapper, "D:\\home-stay-backend\\src\\main\\java\\com\\taikven\\mapper")); // 设置mapperXml生成路径，这里是Mapper配置文件的路径，建议使用绝对路径
//                    })
//                    //策略配置(StrategyConfig)
//                    .strategyConfig(builder -> {
//                        builder.addInclude("evaluate"); // 设置需要生成的表名
//
//
//                        builder.serviceBuilder()
//                                .formatServiceFileName("%sService") //设置service的命名策略,没有这个配置的话，生成的service和serviceImpl类前面会有一个I，比如IUserService和IUserServiceImpl
//                                .formatServiceImplFileName("%sServiceImpl"); //设置serviceImpl的命名策略
//                        builder.controllerBuilder()
//                                .enableRestStyle(); // 开启生成@RestController 控制器，不配置这个默认是Controller注解，RestController是返回Json字符串的，多用于前后端分离项目。
//                        builder.mapperBuilder()
//                                .enableMapperAnnotation() ;//开启 @Mapper 注解，也就是在dao接口上添加一个@Mapper注解，这个注解的作用是开启注解模式，就可以在接口的抽象方法上面直接使用@Select和@Insert和@Update和@Delete注解。
//                    })
//                    .templateEngine(new VelocityTemplateEngine())
//                    .execute(); //执行以上配置
//
//    }

//
//    @Test
//    public void testDel(){
//        bookDao.selectById(123);
//
//    }
//    @Test
//    public void testGetById(){
//        for (int i = 0; i < 35; i++) {
//            Book book = new Book();
//            book.setName("name"+i);
//            book.setDescription("Des"+i);
//            book.setType("tyoe"+i);
//            bookDao.insert(book);
//        }
//    }
//    @Test
//    public void testGetByPage(){
//        IPage page = new Page(1,2);
//        bookDao.selectPage(page,null);
//        System.out.println(page.getCurrent());
//        System.out.println(page.getPages());
//        System.out.println(page.getSize());
//        System.out.println(page.getTotal());
//        System.out.println(page.getRecords());
//
//    }
//    @Test
//    public void testGetAll(){
//
//
//        //控制要查询的字段
//        //SELECT name,description FROM tbl_book WHERE (id = ?)
//        //方法一：
////        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<>();
////        qw.eq(Book::getId,1);
////         qw.select(Book::getName,Book::getDescription);
//
//        //统计
//        QueryWrapper qw = new QueryWrapper();
//        qw.select("count(*) as count,type");
//        qw.groupBy("type");
//        List<Map<String,Object>> res = bookDao.selectMaps(qw);
//
//        System.out.println(res);
//    }
}
