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
