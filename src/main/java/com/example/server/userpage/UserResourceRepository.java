package com.example.server.userpage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResourceRepository extends CrudRepository<UserResourceEntity, Long> {
}
