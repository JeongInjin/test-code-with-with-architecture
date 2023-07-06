package com.me.injin.testcodewithwitharchitecture.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.me.injin.testcodewithwitharchitecture.model.UserStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = true)
@TestPropertySource("classpath:test-application.yml")
@Sql("/sql/user-repository-test-data.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByIdAndStatus_로_유저_데이터를_찾아올_수_있다() throws Exception {
        //given
        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1L, ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void findByIdAndStatus_는_데이터가_없으면_Optional_empty_를_반환한다() throws Exception {
        //given
        //when
        Optional<UserEntity> result = userRepository.findByIdAndStatus(1L, PENDING);

        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void findByEmailAndStatus_로_유저_데이터를_찾을_수_있다() throws Exception {
        //given
        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("injin.dev@gmail.com", ACTIVE);

        //then
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void findByEmailAndStatus_는_데이터가_없으면_Optional_empty_를_반환한다() throws Exception {
        //given
        //when
        Optional<UserEntity> result = userRepository.findByEmailAndStatus("injin.dev@gmail.com", PENDING);

        //then
        assertThat(result.isEmpty()).isTrue();
    }

}
