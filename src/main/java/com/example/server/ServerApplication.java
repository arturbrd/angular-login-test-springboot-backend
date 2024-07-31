package com.example.server;

import com.example.server.adminpage.AdminPageService;
import com.example.server.adminpage.AdminResourceEntity;
import com.example.server.user.Role;
import com.example.server.user.UserEntity;
import com.example.server.user.UserEntityService;
import com.example.server.userpage.UserPageService;
import com.example.server.userpage.UserResourceEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServerApplication.class, args);

        UserEntityService userEntityService = context.getBean(UserEntityService.class);
        userEntityService.addEntity(UserEntity.builder().username("user").password("password").role(Role.ROLE_USER).build());
        userEntityService.addEntity(UserEntity.builder().username("asd").password("123").role(Role.ROLE_USER).build());
        userEntityService.addEntity(UserEntity.builder().username("admin").password("admin").role(Role.ROLE_ADMIN).build());
        System.out.printf("Admin: " + userEntityService.loadUserByUsername("admin"));

        UserPageService userPageService = context.getBean(UserPageService.class);
        userPageService.addEntity(UserResourceEntity.builder().smth("bruh").build());
        userPageService.addEntity(UserResourceEntity.builder().smth("damn").build());

        AdminPageService adminPageService = context.getBean(AdminPageService.class);
        adminPageService.addEntity(AdminResourceEntity.builder().smth("tajne").build());
    }
}
