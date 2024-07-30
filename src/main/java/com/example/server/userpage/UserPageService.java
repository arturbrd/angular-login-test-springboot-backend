package com.example.server.userpage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPageService {
    private final UserResourceRepository repo;

    public List<UserResourceEntity> getEntities() {
        return (List<UserResourceEntity>) repo.findAll();
    }

    public void addEntity(UserResourceEntity entity) {
        repo.save(entity);
    }
}
