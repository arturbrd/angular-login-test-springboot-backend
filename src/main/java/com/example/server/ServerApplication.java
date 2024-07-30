package com.example.server;

import com.example.server.userpage.UserPageService;
import com.example.server.userpage.UserResourceEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServerApplication.class, args);
        UserPageService service = context.getBean(UserPageService.class);
        service.addEntity(UserResourceEntity.builder().smth("bruh").build());
        service.addEntity(UserResourceEntity.builder().smth("damn").build());
    }
}
