package com.example.voyages_api.repositories;

import com.example.voyages_api.TestDataUtil;
import com.example.voyages_api.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTests {

    private final UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTests(UserRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatUserCanBeCreatedAndeRecalled() {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        underTest.save(user);
        Optional<UserEntity> result = underTest.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }


    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        UserEntity userA = TestDataUtil.createTestUserEntityA();
        underTest.save(userA);
        UserEntity userB = TestDataUtil.createTestUserEntityB();
        underTest.save(userB);
        Iterable<UserEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(2)
                .containsExactly(userA, userB);
    }

    @Test
    public void testThatUserCanBeUpdated() {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        underTest.save(user);
        user.setName("Robert");
        underTest.save(user);
        Optional<UserEntity> result = underTest.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }
}
