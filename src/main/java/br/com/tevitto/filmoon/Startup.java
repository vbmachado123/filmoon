package br.com.tevitto.filmoon;

import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);

        System.out.println("> Iniciado: " + DateTime.now());
    }
}
