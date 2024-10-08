# Docker image for springboot file run
# VERSION 0.0.1
# Author: qkj
# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER qkj <1005738053@qq.com>
# 路径
WORKDIR /var/lib/jenkins/workspace/springboot
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为springboot.jar
ADD springboot-core-1.0.0.jar /springboot.jar
# 运行jar包
RUN bash -c 'touch /springboot.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot.jar"]
