package com.example.server.userpage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserPageController {
    private final UserPageService service;

    @GetMapping("/api/user-page")
    public ResponseEntity<?> getUserResources() {
        List<UserResourceEntity> resources = service.getEntities();
        return ResponseEntity.ok(resources);
    }
}
