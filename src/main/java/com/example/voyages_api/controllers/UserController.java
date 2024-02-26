package com.example.voyages_api.controllers;

import com.example.voyages_api.domain.dto.UserDto;
import com.example.voyages_api.domain.entities.UserEntity;
import com.example.voyages_api.mappers.Mapper;
import com.example.voyages_api.services.UserService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Log
public class UserController {

    private final UserService service;
    private final Mapper<UserEntity, UserDto> mapper;

    public UserController(UserService service, Mapper<UserEntity, UserDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        UserEntity userEntity = mapper.mapFrom(user);
        UserEntity savedUserEntity = service.create(userEntity);
        return new ResponseEntity<>(mapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable("id") Long id) {
        Optional<UserEntity> foundUser = service.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto userDto = mapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
