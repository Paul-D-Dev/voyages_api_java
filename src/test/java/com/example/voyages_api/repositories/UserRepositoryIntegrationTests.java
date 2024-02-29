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
import static org.assertj.core.api.Assertions.tuple;

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
        Optional<UserEntity> userResult = underTest.findById(user.getId());
        assertThat(userResult).isPresent();

        UserEntity result = userResult.get();
        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(result.getLastName()).isEqualTo(user.getLastName());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());

        assertThat(result.getCreatedDate()).isEqualTo(result.getUpdatedDate());
    }


    @Test
    public void testThatMultipleUsersCanBeCreatedAndRecalled() {
        UserEntity userA = TestDataUtil.createTestUserEntityA();
        underTest.save(userA);
        UserEntity userB = TestDataUtil.createTestUserEntityB();
        underTest.save(userB);
        Iterable<UserEntity> result = underTest.findAll();
        assertThat(result)
                .hasSize(2)
                .hasOnlyElementsOfType(UserEntity.class)
                .allSatisfy(savedUser -> assertThat(savedUser.getCreatedDate()).isEqualTo(savedUser.getUpdatedDate()))
                .map(UserEntity::getId, UserEntity::getFirstName, UserEntity::getLastName, UserEntity::getEmail)
                .contains(
                        tuple(userA.getId(), userA.getFirstName(), userA.getLastName(), userA.getEmail()),
                        tuple(userB.getId(), userB.getFirstName(), userB.getLastName(), userB.getEmail())
                );
    }

    @Test
    public void testThatUserCanBeUpdated() {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        underTest.save(user);
        user.setFirstName("Robert");
        underTest.save(user);
        Optional<UserEntity> userResult = underTest.findById(user.getId());
        assertThat(userResult).isPresent();

        UserEntity result = userResult.get();
        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(result.getUpdatedDate()).isAfter(result.getCreatedDate());
    }

    @Test
    public void testThatUserCanBeDeleted() {
        UserEntity user = TestDataUtil.createTestUserEntityA();
        underTest.save(user);
        underTest.deleteById(user.getId());
        Optional<UserEntity> result = underTest.findById(user.getId());
        assertThat(result).isEmpty();
    }
}
