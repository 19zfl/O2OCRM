# O2OCRM

#### 技术栈：

**后端**：Maven、SpringBoot、MySQL、Mybatis、Redis、Swagger

**前端**：nvm、Nodejs、Vue.js、Element-UI

**模块**【做一个写一个】：

#### 开发步骤：

1.建库建表；

2.创建一个maven项目；

3.导入相关依赖；

4.建包；

- basic：存放基础类和一些公共类；
- system：后端业务逻辑代码；

5.编写配置文件；

6.编写domain和mapper层代码；

7.单元测试，测试持久层接口与MySQL的连通性；

8.写业务层，控制层；

9.postman测试；

#### 知识点积累：

**注解**：

| 注解名称    | 注解作用                                                     |
| ----------- | ------------------------------------------------------------ |
| @MapperScan | 添加@MapperScan(“com.o2ocrm.\*.mapper”)注解以后，com.o2ocrm.*.mapper包下面所有的接口类，在编译之后都会生成相应的实现类，在不使用@MapperScan前，我们需要直接在Mapper类上面添加注解@Mapper，这种方式要求每一个Mapper类都需要添加此注解，非常麻烦，属于重复劳动。通过使用@MapperScan注解，可以让我们不用为每个Mapper类都添加@Mapper注解。 |