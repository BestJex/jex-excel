package com.jex.controller;

import com.jex.entity.Person;
import com.jex.util.EasyPoiUtil;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Description: easypoi导出导入控制器
 *
 * @author jex
 * @date 2019-02-18 17:19
 */
@Controller
public class EasyPoiController {
    Logger logger = LoggerFactory.getLogger(EasyPoiController.class);

    /**
     * 从数据库获取需要导出的数据到excel
     *
     * 导出【10000】条数据耗时【505】毫秒
     * 导出【50000】条数据耗时【5508】毫秒
     * 导出【100000】条数据耗时【6168】毫秒。【注意：】此方法超过6万条数据会自动新建sheet表格继续保存6000以后的数据
     *
     */
    @RequestMapping("export")
    public void export(HttpServletResponse response) {
        Long start = System.currentTimeMillis();
        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person;
        int total = 100000;
        for (int i = 0; i < total; i++) {
            person = new Person();
            person.setId(i);
            person.setBirthday(DateUtils.addDays(new Date(), i));
            person.setHabit(i + "打球，游泳，登山，打游戏");
            person.setName("姓名" + i);
            person.setSex("男");
            personList.add(person);
        }

        //导出操作
        EasyPoiUtil.exportExcel(personList, "表格标题_花名册", "sheet名称_班级信息", Person.class, "导出表格测试.xlsx", response);
        logger.info("导出【{}】条数据耗时【{}】毫秒", total, System.currentTimeMillis() - start);
    }

    /**
     * 导入excel
     */
    @RequestMapping("importExcel")
    @ResponseBody
    public Map<String, Object> importExcel() {
        Map map = new HashMap();
        String filePath = "D:\\demo\\导入表格测试.xlsx";
        //解析excel
        List<Person> personList = EasyPoiUtil.importExcel(filePath, 1, 1, Person.class);
        //也可以使用MultipartFile,使用 EasyPoiUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        logger.info(ReflectionToStringBuilder.toString(personList));
        System.out.println("导入数据一共【" + personList.size() + "】行");

        //TODO 保存数据库
        //...

        //TODO 返回数据给调用方
        map.put("data", personList);
        return map;
    }

}
