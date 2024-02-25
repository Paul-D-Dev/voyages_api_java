package com.example.voyages_api;

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
}
