# ApkPublishDemo
# 序言
由于公司的项目具有多个环境。每次打包都要手动选择编译类型，打包还得上传fir，和蒲公英。最后截二维码给测试。为了偷懒写了一个gradle 脚本。实现以下功能

 1. 自定义打包类型，可以指定buildType和flavor
 2. 支持定义多种，多个服务器，fir和蒲公英都支持，每种服务都可以配置多个。
 3. 支持自动打包上传，上传使用curl具有上传进度
 4. 支持360加固。只需要简单配置即可实现360自动加固
 5. 支持自动获取git日志作为更新日志
 6. 支持配置邮箱，实现打包完成自动提醒。
##  说明 
**目前支持的Android gradle插件版本有 3.4.1 ,4.0.1 ,4.1.0**
 
 
 在项目根目录的build.gradle文件中可以查看插件版本。4.1.0以上是否支持还没测试，可自行测试。

```groovy
     buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
    	//gradle Android插件版本
        classpath "com.android.tools.build:gradle:4.1.0"
    }
}

```

 
  ## 效果
 口说无凭直接看效果。

|功能|截图|
|---|---|
| 自定义打包类型，可以指定buildType和flavor|![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f42c50ab6fc546c5b60ce449d09345aa~tplv-k3u1fbpfcp-zoom-1.image)
| 通过简单配置，自动生成相关任务双击即可执行。|![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/79c0cf6d4c05400faa00a015a964e7cb~tplv-k3u1fbpfcp-zoom-1.image)|
|发布到相关服务器显示进度信息|![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/61d2a100b43c4fe2bb24b5a301651e62~tplv-k3u1fbpfcp-zoom-1.image)
|上传成功自动生成二维码弹出提示框|![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e0c149a43f26436aa4055aa70229d334~tplv-k3u1fbpfcp-zoom-1.image)
|支持360加固|![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/64b8c643f7f142ea815deb92b17e6339~tplv-k3u1fbpfcp-zoom-1.image)
|配置邮箱，发布成功自动提醒|![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4d6984825094491e94e285d76610ec6c~tplv-k3u1fbpfcp-zoom-1.image)






# 使用
## 下载demo
相关的脚本和使用的demo已经上传至github。需要先下载demo。

> [Publish-GitHub地址](https://github.com/zhuguohui/ApkPublishDemo)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d7198d7f9ee34964b9b4316f57d4f831~tplv-k3u1fbpfcp-zoom-1.image)
## 导入文件
将其中的publish文件夹引入到你的app module中
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/704db614f83347a6ae693552018296ef~tplv-k3u1fbpfcp-zoom-1.image)
###  文件内容说明
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6dc8362344d04b9eb0857831ad2d4672~tplv-k3u1fbpfcp-zoom-1.image)
|文件|功能|
|---|---|
|icon/app_logo.png|存放应用的图标，使用的时候，直接覆盖即可。建议尺寸150px*150px 太大的话还得压缩没必要。也可以不使用这个图片，自行配置logo路径既可
|lib/AppPublish-1.0-SNAPSHOT.jar|提供了弹窗功能，二维码生成功能，发送邮件功能
|log|主要是存放curl访问接口时产生的临时文件，访问结束后会自动删除。这个文件夹不要动它|
|publish.gradle|所有功能的脚本，生成打包，发布，加固任务|

## 配置gradle
### 配置gradle.properties
为了能在多个项目共享配置，建议在gradle的安装目录，配置一个gradle.properties文件。也便于一个部门内统一使用这个打包插件。更多内容可以参考[【Gradle深入浅出】——Gradle配置（一）](https://www.jianshu.com/p/eacd7625cc29)

#### 文件位置
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c2b4826d831845caa0d9d875e44ee491~tplv-k3u1fbpfcp-zoom-1.image)
#### 内容

```shell
#-------------------------App发布助手需要的变量---start---------------------------

#注意配置的路径的时候需要使用转义符，:要加转义符，\也要。

#比如地址是E:\abc 需要写成 E\:\\abc

#360加固配置

#360jar包地址，不能包含中文
TRSJiaGuJirPath=E\:\\WORK\\JiaGu\\360jiagubao_windows_64\\jiagu\\jiagu.jar
#360用户名
TRSJiaGuUserName=xxx
#360密码
TRSJiaGuPassword=yyy
#打包输出路径，不能包含中文
TRSJiaGuOutPath=C\:\\Users\\Administrator\\Desktop\\360JiaGuOut\\

#Email配置

#Email的Smtp服务器地址
TRSPublishEmailServer=smtp.qq.com
#Email地址
TRSPublishEmailAddress=123123123@qq.com
#Email的密码，或者token
TRSPublishEmailPassword=abcdabcd
#Email功能是否启用,true或fasle
TRSPublishEmailEnable=true

#公司的FIR

#api_token

TRSFirApiToken=abc123456789

#公司的蒲公英配置

#ukey
TRSPgyUKey=abc123456789
#api_key
TRSPgyApiKey=abc123456789
#安装的时候使用密码 true 或false
TRSPgyUsePasswordOnInstall=false
#安装密码
TRSPgyInstallPassword=123456
#-------------------------App发布助手需要的变量---end---------------------------
```
**补充:** 这个properties文件最后解析成一个Map<String,String> 所以如果是路径包含了斜杠等符号，需要使用转义。注释中有说明。

定义在该文件中的变量可以在build.gradle中直接使用。但是如果要让其转换成其他值，比如String转Boolean需要这样写。

```groovy
 email {
        smtpServer TRSPublishEmailServer
        emailAddress TRSPublishEmailAddress
        emailPassWord TRSPublishEmailPassword
  		//string转bool
        enable TRSPublishEmailEnable.toBoolean()
    }

```
**因为360加固包不支持中文，所以生成的apk的名字和存放的路径都不能含有中文**
### 项目中配置
这是demo中的完整build.gradle文件。方便大家查看 。相关的说明我都写在注释里了。
```groovy
plugins {
    id 'com.android.application'
}
//引用打包插件
apply from: "publish/publish.gradle"

//配置打包插件
ApkPublish {
	//配置加固信息	
    jiaGu{
        jarPath TRSJiaGuJirPath
        userName TRSJiaGuUserName
        password  TRSJiaGuPassword
        //outPutPath路径中不能包含中文
        outPutPath TRSJiaGuOutPath
    }
	//配置应用信息，在弹窗和而成二维码的时候需要使用
    apkInfo {
        appName "昨日头条"
        appLogo file("/publish/icon/app_logo.png")
    }

	//配置Email，如果不需使用，可以将enable设置为false
    email {
        smtpServer TRSPublishEmailServer
        emailAddress TRSPublishEmailAddress
        emailPassWord TRSPublishEmailPassword
        enable TRSPublishEmailEnable.toBoolean()
    }
	
	//fir服务器，下面可以配置多个
    fir {
    	//一个fir服务器，"公司fir"是他的名字，只有一个属性apiToken
    	//可以配置多个，只有名字唯一就行。该名字只需要配置为apk中的serverName属性
    	//即可实现自动选择该服务器作为上传服务器
        公司fir {
            apiToken TRSFirApiToken
        }
    }
    //蒲公英服务器，下面可以配置多个
    pgy{
    	//一个蒲公英服务器，"公司pgy"是他的名字，需要两个属性
    	//uKey和apiKey
    	//usePasswordOnInstall 表示安装的时候是否需要密码 值是bool
    	//password  安装需要的密码，值是String
    	//可以配置多个，只有名字唯一就行。该名字只需要配置为apk中的serverName属性
    	//即可实现自动选择该服务器作为上传服务器
        公司pgy{
            uKey TRSPgyUKey
            apiKey TRSPgyApiKey
            usePasswordOnInstall TRSPgyUsePasswordOnInstall.toBoolean()
            password TRSPgyInstallPassword
        }
    }
	//配置需要生成的apk文件
    apk {
    	//配置一个apk文件
    	//“正式版”是它的名字，根据这个名字会生成3个任务
    	//打包正式版，发布正式版，加固正式版
    	//buildType 值是String类型，必须是Android配置下的的buildType中的某给值
    	//flavor  值是flavor ，必须是Android配置下的productFlavors中的某个值，没有的话可以自己建一个
    	//serverName 上传的服务器名称，必须是fir配置下，或pgy配置下 某项的名字
    	//useGitLog 是否使用git日志作为更新日志。true的话会提取最近的五条日志作为更新日志
    	//否则更新日志会显示upload by 打包助手
        正式版 {
            buildType "release"
            flavor "official"
            serverName "公司fir"
            useGitLog true
        }

        测试版 {
            buildType "release"
            flavor "BJTest"
            serverName "公司pgy"
            useGitLog true
        }

    }

}

android {
    compileSdkVersion 30
    buildToolsVersion "30.0.3"

    defaultConfig {
        applicationId "com.zgh.apkpublish"
        minSdkVersion 16
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    //需要配置签名，这样才可以实现全过程的自动化
    signingConfigs {
        release {
            storeFile file('../key/apk_publish.jks')
            storePassword 'admin123'
            keyPassword 'admin123'
            keyAlias = 'publish'
        }
    }

    buildTypes{
        release{
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            	//设置需要使用的签名配置
            signingConfig signingConfigs.release
        }
        debug{
        	//设置需要使用的签名配置
            signingConfig signingConfigs.release
        }
    }
	
	//不同的产品风味
    productFlavors {
        flavorDimensions "api"
        official {
            buildConfigField "String", "BASE_URL", "\"正式环境\""
        }
        BJTest{
            buildConfigField "String", "BASE_URL", "\"测试环境\""
        }
    }
}


dependencies {

    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
}
```
### 补充说明
#### 1.360加固配置

[360加固下载地址](https://jiagu.360.cn/#/global/download)
安装成功以后它的安装地址是![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dd8715890545418a9d3326cfbee18bf9~tplv-k3u1fbpfcp-zoom-1.image)
jar包就在这个位置
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/793a7a0cf48140a2a31697f7b28db242~tplv-k3u1fbpfcp-zoom-1.image)
#### 2.curl乱码
win10 自带了curl。但是上传的时候如果包含了中文会出现乱码。比较简单的解决办法就是修改系统的编码。

[小编教你把win10系统默认编码为utf8](http://www.xitongcheng.com/jiaocheng/win10_article_65924.html)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/db1a0a434275450593d1f3f72797a497~tplv-k3u1fbpfcp-zoom-1.image)
####  3.邮箱配置
建议使用QQ邮箱，手机上安装QQ邮箱可以实时收到提醒。
进入QQ邮箱->设置->账户 



![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2a1d745ede9845fc89e84f4d8c1ca684~tplv-k3u1fbpfcp-zoom-1.image)
往下翻
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/454b72ca50af4e0384e1bc8a423759d4~tplv-k3u1fbpfcp-zoom-1.image)
#### 4.Fir Token 获取
[fir官网](https://www.betaqr.com/)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8322d4813c2248769315671ea3f38e32~tplv-k3u1fbpfcp-zoom-1.image)
#### 5.蒲公英 uKey，api key获取
[蒲公英首页](https://www.pgyer.com/)
点击API信息
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d9c3c4022ada4e85a64e1db6bd60fb7d~tplv-k3u1fbpfcp-zoom-1.image)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/92876310de6240819d8679ea129fbadb~tplv-k3u1fbpfcp-zoom-1.image)



# 功能拓展
## GUI扩展
我的GUI是自己编写的库也上传到github了，需要修改的可以自行fork。
[GUI](https://github.com/zhuguohui/AppPublish)

## gradle扩展
所有功能实现都在publish.gradle中。可以自行阅读，扩展功能。

也可以参考以下内容实现扩展

> [Android Gradle学习(五)：Extension详解](https://www.jianshu.com/p/58d86b4c0ee5) 这是一个系列，值得学习

> [【Gradle Task】FIR上传脚本（cURL方式、关联git提交次数、更新日志）
](https://www.jianshu.com/p/12b53f8dd768)
> 
> [curl 的用法指南](https://www.ruanyifeng.com/blog/2019/09/curl-reference.html)
> [Android Gradle 插件版本说明](https://developer.android.google.cn/studio/releases/gradle-plugin)

### 下载Android gradle 插件
分享一个下载gradle插件源码的小技巧，只需要将你需要的gradle插件作为依赖配置在你的项目中![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bae59e7ea05a43d1a1643809a2fff19a~tplv-k3u1fbpfcp-zoom-1.image)
然后gradle就会下载代码，可以通过反编译查看一些Android gradle不同版本间实现的差异。这样可以提高脚本的兼容性。
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/412400bb254b4aa99ca86d66f1e763aa~tplv-k3u1fbpfcp-zoom-1.image)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0295ade6eda74563a25c730241c955db~tplv-k3u1fbpfcp-zoom-1.image)




