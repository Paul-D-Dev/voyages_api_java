package com.example.voyages_api;

import com.example.voyages_api.domain.dto.UserDto;
import com.example.voyages_api.domain.entities.UserEntity;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static UserEntity createTestUserEntityA() {
        return UserEntity.builder()
                .id(1L)
                .name("Georges")
                .build();
    }

    public static UserEntity createTestUserEntityB() {
        return UserEntity.builder()
                .id(2L)
                .name("Albert")
                .build();
    }

    public static UserDto createTestUserDtoA() {
        return UserDto.builder()
                .id(1L)
                .name("Georges")
                .build();
    }

}
