
# 提示： 代码是前几年写的了 当时水平和现在可能有较大差异 也不打算更新该项目了  各位可以移步csdn找我 csdn博客长期都有在更新 欢迎交流技术
# csdn: 孟秋与你 地址： https://blog.csdn.net/qq_36268103?type=blog


# springboot-DDD
springboot综合项目, DDD四层架构 springboot整合rocketmq、aop切面、定时器、事务、线程池、发送邮件、并发安全问题、全局异常处理、websocket、动态数据库、maven聚合工程及正确打包方式、spring基础知识等，是一个较为综合的demo 可用于脚手架开发 


## 软件架构说明

### springboot-core: 核心代码 DDD领域模型（四层架构）

interfaces:接口层 对外/面向前端层 
application: 应用层/业务层 
domain: 领域层 由于只是demo 领域层目前只放了少量公用东西 
infrastructure: 基础设施层 包括注解、仓储（数据库）层 主要提供基础支持

### springboot-pojo: 
其中 dto/query/vo直接剥离出来 放在该子模块下面

## 安装教程
1. 下载本项目 在ide导入
2. 下载rocketmq
3. rocketmq 以windows为例 不熟可以看教程 [https://blog.csdn.net/qq_36268103/article/details/121858617] 
4. 配置邮箱 以QQ邮箱为例 不熟可以看教程[https://blog.csdn.net/qq_36268103/article/details/121857079] 

5. 本项目包含了复现log4j漏洞示例 务必阅读 Log4jErrorController 类里面的注释 把log4j-core包去除 并开放默认日志

6. 如果不需要邮件或rocketmq功能 可以把相关代码和配置注释
