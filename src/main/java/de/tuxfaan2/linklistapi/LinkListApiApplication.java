package de.tuxfaan2.linklistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(LinkListApiProperties.class)
public class LinkListApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkListApiApplication.class, args);
    }

}
