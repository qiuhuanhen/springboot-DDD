# springboot-DDD
DDD四层架构项目 springboot整合rocketmq、aop切面、定时器、事务、线程池、发送邮件、并发安全问题、全局异常处理、websocket、动态数据库、maven聚合工程及正确打包方式、spring基础知识等，是一个较为综合的demo 可用于脚手架开发 

介绍
{** springboot 合集** 本项目是可以作为一个脚手架 也可以作为学习项目 按照DDD四层架构命名 整合了java线程池、spring中的线程池应用、websocket、线程安全问题解决、切面、rocketmq消息队列、动态数据库、邮件发送等；覆盖了较多企业中可能遇到的场景，其中rocketmq封装了一个jar包，jar包按照springboot风格写的。项目中，包括pom.xml 都写了详细的注释 这些注释同样是很重要的 甚至有的比代码还重要！更多技术教程请看 https://blog.csdn.net/qq_36268103}


软件架构说明

springboot-core: 核心代码 DDD领域模型（四层架构）

interfaces:接口层 对外/面向前端层 
application: 应用层/业务层 
domain: 领域层 由于只是demo 领域层目前只放了少量公用东西 
infrastructure: 基础设施层 包括注解、仓储（数据库）层 主要提供基础支持

springboot-pojo: 其中 dto/query/vo直接剥离出来 放在该子模块下面

安装教程
下载本项目 在ide导入
下载rocketmq
rocketmq 以windows为例 不熟可以看教程 [https://blog.csdn.net/qq_36268103/article/details/121858617] (https://blog.csdn.net/qq_36268103/article/details/121858617)
配置邮箱 以QQ邮箱为例 不熟可以看教程[https://blog.csdn.net/qq_36268103/article/details/121857079] (https://blog.csdn.net/qq_36268103/article/details/121857079)

本项目包含了复现log4j漏洞示例 务必阅读 Log4jErrorController 类里面的注释 把log4j-core包去除 并开放默认日志

如果不需要邮件或rocketmq功能 可以把相关代码和配置注释
