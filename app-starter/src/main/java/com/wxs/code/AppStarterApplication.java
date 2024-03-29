package com.wxs.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class AppStarterApplication  {

    public static void main(String[] args) {
        ApplicationContext ctx=  SpringApplication.run(AppStarterApplication.class, args);
        System.out.println(Arrays.stream(ctx.getBeanDefinitionNames()).toList());
    }

}
