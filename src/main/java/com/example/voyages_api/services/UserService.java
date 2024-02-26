package com.example.voyages_api.services;

import com.example.voyages_api.domain.entities.UserEntity;

public interface UserService {

    UserEntity create(UserEntity user);
}
