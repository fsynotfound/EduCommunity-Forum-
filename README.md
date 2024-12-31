# 基于SpringBoot的校园论坛
#### 介绍
{**开发了一个具有注册、登录、发帖、评论、点赞和消息传递功能的校园论坛**}

#### 技术架构

- Spring Boot
- Spring、Spring MVC、MyBatis
- Redis、Kafka、Elasticsearch
- Spring Security、Spring Actuator

#### 开发环境

- 构建工具：Apache Maven
- 集成开发工具：IntelliJ IDEA
- 数据库：MySQL、Redis
- 应用服务器：Apache Tomcat
- 版本控制工具：Git

#### 软件架构
 **基础功能** 

1. 邮箱设置
- 启用客户端SMTP服务
- Spring Email：
    ——导入jar包。
    ——邮箱参数配置。
    ——使用JavaMailSender发送邮件。
- 模板引擎：
    ——使用Thymeleaf发送HTML邮件。

2. 注册功能使用邮箱验证。
- 访问注册页面：
    ——点击顶部区域内的链接，打开注册页面。
- 提交注册数据：
    ——通过表单提交数据。
    ——服务端验证账号是否已存在、邮箱是否已注册。
    ——服务端发送激活邮件。
- 激活注册账号：
    ——点击邮件中的链接，访问服务端的激活服务。

3. HTTP的基本性质
- HTTP是无状态，有会话的：
    ——HTTP是简单的。
    ——HTTP是可扩展的。
    ——HTTP是无状态的，有会话的。
- Cookie：
    ——是服务器发送到浏览器，并保存在浏览器端的一小块数据。
    ——浏览器下次访问该服务器时，会自动携带块该数据，将其发送给服务器。
- Session：
    ——是JavaEE的标准，用于在服务端记录客户端信息。
    ——数据存放在服务端更加安全，但是也会增加服务端的内存压力。
4. Kaptcha

- 导入jar包。
- 编写Kaptcha配置类。
- 生成随机字符、生成图片。

5. 登录退出功能
- 使用Redis存储验证码
——验证码需要频繁的访问与刷新，对性能要求较高。
——验证码不需永久保存，通常在很短的时间后就会失效。
——分布式部署时，存在Session共享的问题。
- 使用Redis存储登录凭证
——处理每次请求时，都要查询用户的登录凭证，访问的频率非常高。
- 使用Redis缓存用户信息
——处理每次请求时，都要根据凭证查询用户信息，访问的频率非常高。

6. 显示登录信息
- 拦截器示例：
    ——定义拦截器，实现HandlerInterceptor。
    ——配置拦截器，为它指定拦截、排除的路径。
- 拦截器应用：
    ——在请求开始时登录查询用户。
    ——在本次请求中持有用户数据。
    ——在模板视图上显示用户数据。
    ——在请求结束时清理用户数据。
- 上传文件：
    ——上传头像，访问账号设置页面，通过MultipartFile处理。

7. 账号设置
- 上传文件：
    ——请求：必须是POST请求。
    ——表单：enctype="multipart/form-data"。
    ——Spring MVC:通过MultipartFile处理上传文件。
- 开发步骤：
    ——访问账号设置页面。
    ——上传头像。
    ——获取头像。

8. 检查登录状态
- 使用拦截器：
——在方法前标注自定义注解。
——拦截所有请求，只处理带有该注解的方法。
- 自定义注解：
——常用的元注解：
@Target、 @Retention、 @Document、 @Inherited。
——如何读取注解：
Method.getDeclaredAnnotations()
Method.getAnnotation(Class<T> annotationClass)

 **核心功能**
1. 过滤敏感词
- 前缀树：
    ——名称：Trie、字典树、查找树。
    ——特点：查找效率高，消耗内存大。
    ——应用：字符串检索、词频统计、字符串排序等。
- 敏感词过滤器：
    ——定义前缀树。
    ——根据敏感词，初始化前缀树。
    ——编写过滤敏感词的方法 。
2. 发布帖子、添加回复
- AJAX：
——使用AJAX,网页能够将增量更新呈现在页面上，而不需要刷新整个页面。
——可以针对帖子评论、针对回复评论、针对评论评论。
3. 私信列表
- 私信列表：
——查询当前用户的会话列表。
——每个会话只显示一条最新的私信。
- 私信详情：
——查询某个会话所包含的私信。
- 发送私信：
——采用异步的方式发送私信。
——发送成功后刷新私信列表。
——访问私信详情时，将显示的私信设置为已读状态。
4. 统一处理异常
- @ControllerAdvice：
——用于修饰类，表示该类是Controller的全局配置类。
——在此类中，可以对Controller进行如下三种全局配置。
——异常处理方案、绑定数据方案、绑定参数方案。
- @ExceptionHandler：
——用于修饰方法，该方法会在Controller出现异常后被调用，用于处理捕获到的异常。
5. Redis
- Redis是一款基于键值对的NoSQL数据库，它的值支持多种数据结构：
——字符串（strings)、哈希（hashes)、列表（lists)、集合（sets)、有序集合（sorted sets)等。
- Redis将所有的数据都存放在内存中，所以它的读写性能十分惊人。
- 同时，Redis还可以将内存中的数据以快照或日志的形式保存到硬盘上，以保证数据的安全性。
- Redis典型的应用场景包括：缓存、排行榜、计数器、社交网络、消息队列等。
6. 点赞（Redis）-点赞列表
- 点赞：
——支持对帖子、评论点赞。
——第1次点赞，第2次取消点赞。
- 首页、详情页：
——统计帖子的点赞数量和状态。
7. 关注取消关注（Redis）-关注列表
- 需求:
——开发关注、取消关注功能。
——统计用户的关注数、粉丝数。
- 关键
——若A关注了B,则A是B的Follower(粉丝）,B是A的Followee(目标）。
——关注的目标可以是用户、帖子、题目等，在实现时将这些目标抽象为实体。
8. 使用kafka发送和显示系统通知
- 触发事件：
——评论后，发布通知。
——点赞后，发布通知。
——关注后，发布通知。
- 处理事件：
——封装事件对象。
——开发事件的生产者。
——开发事件的消费者。
- 显示通知
——显示评论、点赞、关注三种类型通知。
——各种通知的详情页面。
——显示三种通知的未读消息，以及头部总的未读消息。
9. 开发社区搜索功能
- 搜索服务
——将帖子保存至Elasticsearch服务器。
——从Elasticsearch服务器删除帖子。
——从Elasticsearch服务器搜索帖子。
- 发布事件
——发布帖子时，将帖子异步的提交到Elasticsearch服务器。
——增加评论时，将帖子异步的提交到Elasticsearch服务器。
——在消费组件中增加一个方法，消费帖子发布事件。
- 显示结果
——在控制器中处理搜索请求，在HTML上显示搜索结果。
10. 使用SpringSecurity进行权限控制
- 登录检查:
——之前采用拦截器实现了登录检查，这是简单的权限管理方案，现在将其废弃。
- 授权配置
——对当前系统内包含的所有的请求，分配访问权限（普通用户、版主、管理员）。
- 认证方案
——绕过Security认证流程，采用系统原来的认证方案。
- CSRF配置
——防止CSRF攻击的基本原理，以及表单、AJAX相关的配置。
11. 置顶、加精、删除
- 功能实现：
——点击置顶，修改帖子的类型。
——点击“加精”、“删除”，修改帖子的状态。
- 权限管理：
——版主可以执行“置顶”、“加精”操作。
——管理员可以执行“删除”操作。
- 按钮显示：
——版主可以看到“置顶”、“加精”按钮。
——管理员可以看到“删除”按钮。

#### 使用说明

1.  aop、web、thyleaf、devTools
2.  jdk1.8
3.  maven3.6.2
4.  mysql5.7.3、redis


#### 扩展知识
1. Kafka：
- Kafka是一个分布式的流媒体平台。
- 应用：消息系统、日志收集、用户行为追踪、流式处理。
- Kafka特点：
——高吞吐量、消息持久化、高可靠性、高扩展性。
- Kafka术语：
——Broker、Zookeeper
——Topic、Partition、Offset
——Leader Replica 、 Follower Replica
2. Elasticsearch

- 一个分布式的、Restful风格的搜索引擎。
- 支持对各种类型的数据的检索。
- 搜索速度快，可以提供实时的搜索服务。
- 便于水平扩展，每秒可以处理PB级海量数据。

- Elasticsearch术语：
——索引、类型、文档、字段。
——集群、节点、分片、副本。
3. Spring Security

- Spring Security是一个专注于为Java应用程序提供。
- 身份认证和授权的框架，它的强大之处在于它可以。
- 轻松扩展以满足自定义的需求。

- 特征
——对身份的认证和授权提供全面的、可扩展的支持。
——防止各种攻击，如会话固定攻击、点击劫持、csrf攻击等。
——支持与Servlet API、Spring MVC等Web技术集成。
4. Redis高级数据类型
- HyperLogLog
——采用一种基数算法，用于完成独立总数的统计。
——占据空间小，无论统计多少个数据，只占12K的内存空间
——不精确的统计算法，标准误差为0.81%。
- Bitmap
——不是一种独立的数据结构，实际上就是字符串。
——支持按位存取数据，可以将其看成是byte数组。
——适合存储索大量的连续的数据的布尔值。



