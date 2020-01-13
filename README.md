## 简介
easypoi 或者 easyexcel 实现excel导入导出。

## 文档
#### easypoi 教程
http://easypoi.mydoc.io/#text_226083

https://opensource.afterturn.cn/doc/easypoi.html

## 参考
#### easypoi 参考
http://easypoi.mydoc.io/

https://gitee.com/lemur/easypoi

https://github.com/jasonhouseg/springboot-easypoi


#### easyexcel 参考
https://yq.aliyun.com/articles/690407

https://github.com/alibaba/easyexcel

https://github.com/Uetty/common-excel

## 注意
#### 对象创建错误处理
编写实体类如Person时注意：必须要有空构造函数，否则调用EasyPoiUtil.importExcel(file, Person.class, importParams)时会报错“对象创建错误”！

#### 性能比较
加入阿里easyexcel导出测试，阿里easyexcel效率貌似高于easypoi，而且导出文件也比较小。