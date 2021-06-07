//package is a Java keyword, keyword package should be at the top of a Java file
//Application.java파일에 속하는 Application class가 속하는 패키지가 com.jojoldu.webservice2라고 package 키워드를 통해서 지정
package com.jojoldu.webservice2;

//org.springframework.boot(package)에 있는 SpringApplication 클래스를 Application.java로 가지고 오는 코드
//In Java, all package names are lowercase and a class name should begin with a capital letter
//If a class name consists of more than 1 word, the first character of each word in a class name should be uppercase
//means bring class SpringApplication(SpringApplication.java) which is inside org.springframework.boot to the current file(Application.java)
import org.springframework.boot.SpringApplication;

//means bring class SpringBootApplication(SpringApplication.java) which is inside org.springframework.boot.autoconfigure to the current file(Application.java)
import org.springframework.boot.autoconfigure.SpringBootApplication;

//means bring class EnableJpaAuditing which is inside org.springframework.data.jpa.repository.config to the current file(Application.java)
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//SpringBoot이 제공하는 어노테이션
//@SpringBootApplication은 @Configuration,@EnableAutoConfiguration,@ComponentScan들의 기능을 제공함
    //@Configuration marks the class(Application) as a Configuration class for Java-based configuration(instead of XML configuration)
    //@ComponentScan enables component scanning so that web controller classes(UserController,WebController) and other components will be
        //automatically discovered and registered as beans(Java objects) in Spring's Application context
        //All the classes(UserController,WebController) with @Controller are discovered by this annotation @Componentscan
    //@EnableAutoConfiguration enables the auto-configuration feature of SpringBoot, which automatically configure many things for me
    //@EnableAutoConfiguration marks the class as a Bootstrap class, meaning I can run this class Application as a normal Java class
        //for example, running its jar file(spring-webservice2-0.0.1-SNAPSHOT.jar) in command line: java -jar spring-webservice2-0.0.1-SNAPSHOT.jar

//이 프로젝트(spring-webservice2)의 springboot version(2.4.4 (이 버전 숫자는 build.gradle에 나와있고 리눅스 원격서버에서 jar file 실행시키면 나옴 2.4.4라고 ))이 1.2 이상이여서 @SpringBootApplication 사용가능한거임
//프로젝트 스프링부트버전이 1.2미만이면 @SpringBootApplication 사용못하고 대신 @Configuration,@ComponentScan, @EnableAutoConfiguration 사용해야됨 필요하다면
@EnableJpaAuditing//JPA auditing을 활성화 하는것. JPA auditing;//side note, CrudRepository is the interface for generic CRUD operations
public class Application{ //class Application is the entry point
    
    //when the java interpreter executes an application, it starts by calling the class's main method, which then calls all the other methods
    public static void main(String[] args){//every Java application must contain a main method
                                               //public indicates that the main method can be called from anywhere
                                           //static indicates that main method is a class method
                                           //void means main method is not returning any value
                                           //args is an String array containing command-line arguments
        //SpringContext를 실행하고 application을 구동한다: 첫번쨰 인자는 Spring component, 2번째 인자는 string array containing command line arguments
        SpringApplication.run(Application.class,args);

    }

}

