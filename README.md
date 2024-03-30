# O2OCRM

### 技术栈：

#### 后端：

Maven、SpringBoot、MySQL、Mybatis、Redis、Swagger

#### 前端：

nvm、Nodejs、Vue.js、Element-UI

#### 模块【做一个写一个】：

部门管理模块、员工管理模块

目前在develop分支上进行feature的完善

#### 接口文档地址：

http://localhost:8080/swagger-ui.html

### 知识点积累：

#### CORS、Access-Control-Allow-Origin：跨域

- 跨域是浏览器的一种安全策略
- 异步发送的请求，请求的协议/IP/端口与当前域不匹配，只要有其中一个不匹配，就会出现跨域问题

#### 缓存：

- 减轻数据库内存开销，减少数据库服务器压力
- 提高数据的访问速度

分布式缓存实现方案：Redis

#### **Redis**：

##### 常见5种数据类型

| name   | mean             |
| ------ | ---------------- |
| string | 字符串           |
| list   | 队列或者栈数据   |
| hash   | 对象数据         |
| set    | 无序且唯一的数据 |
| zset   | 有序且唯一的数据 |

##### 默认配置：

- 端口：6379
- 密码：requirepass

##### 修改默认密码：

- 打开redis.windows.conf文件
- 443行将注释删除，requirepass后面空一格再加上密码

##### 快速启动：

- 新建bat文件
- 内容为：redis-server.exe  redis.windows.conf

#### 注解：

| 注解名称                                            | 注解作用                                                     |
| --------------------------------------------------- | ------------------------------------------------------------ |
| @Controller                                         |                                                              |
| @GeneratedValue(strategy = GenerationType.IDENTITY) | 打在实体类id字段上，这样新增操作完成之后就能立即拿到新增数据的id |
| @JsonInclude(JsonInclude.Include.NON_EMPTY)         | 用来给实体类的某个字段，如果这个字段为空，则进行忽略         |
| @MapperScan                                         | 添加@MapperScan(“com.o2ocrm.\*.mapper”)注解以后，com.o2ocrm.*.mapper包下面所有的接口类，在编译之后都会生成相应的实现类，在不使用@MapperScan前，我们需要直接在Mapper类上面添加注解@Mapper，这种方式要求每一个Mapper类都需要添加此注解，非常麻烦，属于重复劳动。通过使用@MapperScan注解，可以让我们不用为每个Mapper类都添加@Mapper注解。 |
| @PathVariable                                       |                                                              |
| @RequestBody                                        |                                                              |
| @ResponseBody                                       |                                                              |
| @RestControllerAdvice                               |                                                              |
| @Transient                                          | 被注解的字段用于接受sql中的字段传递，不参与CRUD              |
| @Valid                                              | 表示所有前端传递的形参参数必须通过规则校验，在实体类的字段上搭配@Size和@NotBlank等注解使用 |

#### 请求status：

| 状态码 | 含义                                          |
| ------ | --------------------------------------------- |
| 400    | 参数错误                                      |
| 403    | CORS、Access-Control-Allow-Origin，未配置跨域 |
| 405    | 请求方式错误                                  |

#### Swagger常用注解：

| 注解              | 位置                                                         | 参数                  | 效果                                                         |
| ----------------- | ------------------------------------------------------------ | --------------------- | ------------------------------------------------------------ |
| @Api              | ![image-20240309171359014](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091714113.png) | value、tag、hidden    | ![image-20240309171649294](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091716323.png) |
| @ApiOperation     | ![image-20240309172259458](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091722502.png) | value、notes、hidden  | ![image-20240309172911335](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091729367.png) |
| @ApiParam         | ![image-20240309173155907](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091731939.png) | value、name、required | ![image-20240309173457832](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091734876.png) |
| @ApiModel         | ![image-20240309173844147](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091738180.png) | value、desc           | ![image-20240309173812239](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091738271.png) |
| @ApiModelProperty | ![image-20240309174207017](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091742048.png) | value                 | ![image-20240309174253558](https://gitee.com/coder_zfl/markdown-image-cloud-drive/raw/master/markdown/202403091742592.png) |

太多了，需要时再去了解

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

测试：分支合并，此为develop分支