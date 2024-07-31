package com.example.server.adminpage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminResourceRepository extends CrudRepository<AdminResourceEntity, Long> {
}
