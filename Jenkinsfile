def getHost(){
    def remote = [:]
    remote.name = 'aliyun'
    remote.host = 'xx.xxx.xxx你的主机地址'
    remote.user = '你用来登陆linux的账号'
    remote.port = 22
    remote.password = '密码'
    remote.allowAnyHosts = true
    return remote
}
pipeline {
    agent any
    environment {
        server=""
        ACTIVE_PROFILE="dev"     // 当前环境
        BRANCH_NAME="master"    // 代码分支名称
        HOST_EXPOSE_PORT=8586        // docker对外端口
        CONTAINER_EXPOSE_PORT=8585   // 项目端口

        JAR_NAME="springboot-core-1.0.0.jar"   //jar包名
        JAR_OTHER_NAME="springboot.jar" // jar包别名
        GIT_USERNAME="gitee账号"
        GIT_PASSWORD="gitee密码"
        GIT_ADDR="https://${GIT_USERNAME}:${GIT_PASSWORD}@gitee.com:xxx/xxx.git"  //项目地址
        JOB_NAME="springboot"
        JENKINS_WORKSPACE="/root/.jenkins/workspace"    // jenkins存放文件的地址
        PROJECT_NAME="springboot-core"   // 项目名称
        CONTAINER_NAME="${JOB_NAME}"                // 容器名称
        IMAGE_NAME="${JOB_NAME}"                    // 镜像名
        IMAGE_TAG="v1.0.0"                          // 镜像版本号
        APP_SERVER_JOB_PATH="/var/lib/jenkins/workspace/${JOB_NAME}" // 应用服务器工程项目根目录
        APP_SERVER_PROJECT_PATH="${APP_SERVER_JOB_PATH}/${PROJECT_NAME}/target" // 项目目录绝对路径

    }
    stages {
        stage('InitContainerServer'){
            steps {
                script {
                    server = getHost()
                }
            }
        }
        stage("Checkout") {
            steps {
                echo "从Git下载项目源码"
                script {
                    ssh remote: server, command: """
                    if test -e ${APP_SERVER_JOB_PATH};then
                        rm -rf ${APP_SERVER_JOB_PATH}
                        whoami
                        git clone -b ${BRANCH_NAME} ${GIT_ADDR} ${APP_SERVER_JOB_PATH}
                    else
                        git clone -b ${BRANCH_NAME} ${GIT_ADDR} ${APP_SERVER_JOB_PATH}
                    fi
                    """
                }
            }
        }
                stage("Build") {
            steps {
                echo "开始maven编译构建"
                sh 'whoami'
                sh 'pwd'
                sh "mvn  clean package -P ${ACTIVE_PROFILE} -U -Dmaven.test.skip=true"
                sh "mv -f ${APP_SERVER_PROJECT_PATH}/${JAR_NAME}  ${APP_SERVER_JOB_PATH}"
                echo "maven构建成功..."

            }
        }


        // 注意点1： jenkins需要安装SSH Pipeline Steps 插件 用于sshCommand 命令 并可以在控制台看到脚本内容做了什么
        // 不安装的话 sshCommand替换成ssh 也是可以执行的 但是很多时候看不到脚本里面做了什么
        // 注意点2 $()和``是一个含义  ``是shell通用  $()有的不支持 但可读性更强
        // 注意点3 """ 见到$ 就会当成一个变量 但是下面有$()/``  所以需要替换成'''
        // 注意点4 awk因为里面有$1存在 所以同样的需要单引号 ''

        stage("CleanContainer") {
            steps {
                echo "清理旧容器中..."
                sh '''docker stop `docker container ls -a|grep -w ${JAR_NAME}|awk '{print $1}'` ||true'''
                sh '''docker stop `docker container ls -a|grep -w ${HOST_EXPOSE_PORT}|awk '{print $1}'` ||true'''
                sh 'sleep 3s'
                sh '''docker rm -f `docker container ls -a|grep -w  ${JAR_NAME}|awk '{print $1}'` ||true'''
                sh '''docker rm -f `docker container ls -a|grep -w  ${HOST_EXPOSE_PORT}|awk '{print $1}'` ||true'''

                sh 'docker rmi -f ${JAR_NAME}||true'

                echo "清理旧容器成功 开始构建docker"

            }
        }

        stage("Deploy") {
            steps {

                sh  """
                        pwd
                        whoami
                        docker build -t  ${JAR_NAME} .
                        echo "构建docker成功..."
                    """

                echo "开始部署"
                sh 'docker run -d -p ${HOST_EXPOSE_PORT}:${CONTAINER_EXPOSE_PORT} -t ${JAR_NAME} >output 2>&1'

                echo "部署完毕"
            }
        }
        stage("Clean") {
            steps {
                echo "清理工作"
            }
        }
    }
    post {
        always {
            echo "工作流执行完毕"
        }
        failure {
            echo "工作流执行失败！ :("
        }
        success {
            echo "工作流执行成功！ ^o^"
        }
    }
}
