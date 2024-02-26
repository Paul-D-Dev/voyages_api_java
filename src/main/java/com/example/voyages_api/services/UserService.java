package com.example.voyages_api.services;

import com.example.voyages_api.domain.entities.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity create(UserEntity user);

    Optional<UserEntity> findOne(Long id);
}
