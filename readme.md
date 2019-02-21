# maven 自定义组件

### 赞赏

请作者喝杯咖啡吧！

![idea-plugin](/doc/weix.png)


## 1、组件命名

插件命名为` <myplugin>-maven-plugin`。

不要使用`maven-<myplugin>-plugin`，因为该命名是 Maven 团队维护官方插件的保留命名方式，使用这个命名方式会侵犯 Apache Maven 商标。

## 2、自定义组件实现类

Mojo 就是 Maven plain Old Java Object。每一个 Mojo 就是 Maven 中的一个执行目标（executable goal），而插件则是对单个或多个相关的 Mojo 做统一分发。一个 Mojo 包含一个简单的 Java 类。插件中多个类似 Mojo 的通用之处可以使用抽象父类来封装。

继承`AbstractMojo`，编写自己的组件执行代码。

如DemoMojo,定义计算求和的组件，定义num1，num2，记得需要设置get，set方法。
```
package com.zxbking.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by zhangxb on 2019/2/21.
 */
@Mojo(name = "sumplugin", defaultPhase = LifecyclePhase.PACKAGE)
public class DemoMojo extends AbstractMojo {
    @Parameter
    private int num1;
    @Parameter
    private int num2;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("num1 = " +num1 );
        System.out.println("num2 = " +num2 );
        System.out.println("sum = "+(num1+num2) );
    }


    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}

```

## 3、发布组件

完成mojo类进行编辑后，执行`mvn clean install`添加到本地库。或者发布到远程库。

## 4、引入组件

在需要使用这个组件的项目的pom文件中，增加一下代码

```
<build>
        <plugins>
            <plugin>
                <groupId>com.zxbking.plugin</groupId>
                <artifactId>demo-maven-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
                <configuration>
                    <num1>10</num1>
                    <num2>20</num2>
                </configuration>
            </plugin>
         <plugins>
<build>
```

## 5、运行组件

使用命令启动
```
mvn com.zxbking.plugin:demo-maven-plugin:1.0-SNAPSHOT:sumplugin
```
同个项目里面，组件可以直接使用`mvn demo:sunplugin`启动运行。不同项目则需要对应的maven库里面存在。



使用idea工具启动，双击启动

![idea-plugin](/doc/idea-plugin.png)


## 6、注意事项

如果设置的组件参数没有设置get/set方法，在配置pom里面会存在找不到参数的问题。

## 7、代码地址

https://github.com/zxbking/demo-maven-plugin