package com.example.server.adminpage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPageController {
    private final AdminPageService service;

    @GetMapping("/api/admin-data")
    public ResponseEntity<?> getAdminResources() {
        List<AdminResourceEntity> resources = service.getEntities();
        return ResponseEntity.ok(resources);
    }
}
