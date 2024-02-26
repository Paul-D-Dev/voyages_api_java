package com.example.voyages_api.repositories;

import com.example.voyages_api.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
