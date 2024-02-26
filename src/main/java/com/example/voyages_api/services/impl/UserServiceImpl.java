package com.example.voyages_api.services.impl;

import com.example.voyages_api.domain.entities.UserEntity;
import com.example.voyages_api.repositories.UserRepository;
import com.example.voyages_api.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserEntity create(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return repository.findById(id);
    }
}
