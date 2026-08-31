package com.rookies6.myspringboot4project.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyRunner implements ApplicationRunner {
    @Value("${spring.application.name}")
    String applicationName;

    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyRunner run() 호출됨!!");
        System.out.println("Application Name: " + applicationName);

        //Consumer 인터페이스를 Anonymous Inner Class로 표현
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("arg name = " + s);
            }
        });

        //args.getOptionNames() 메서드의 리텁타입은 Set<String>
        //Iterable의 forEach(Consumer)
        //Consumer의 추상메서드 void accept(T t)
        //Consumer 인터페이스를 람다식으로 표현
        args.getOptionNames().forEach(name -> System.out.println("name = " + name));

        //Consumer 인터페이스를 Method Reference로 표현
        args.getOptionNames().forEach(System.out::println);

        System.out.println("${myboot.name} = " + name);
        System.out.println("${myboot.age} = " + age);
        System.out.println("${myboot.fullName} = " + environment.getProperty("myboot.fullName"));
    }
}
