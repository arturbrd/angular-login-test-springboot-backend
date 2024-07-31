package com.example.server.adminpage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPageService {
    private final AdminResourceRepository repo;

    public List<AdminResourceEntity> getEntities() {
        return (List<AdminResourceEntity>) repo.findAll();
    }

    public void addEntity(AdminResourceEntity entity) {
        repo.save(entity);
    }
}
