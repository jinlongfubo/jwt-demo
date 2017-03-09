package com.tanghuan.jwt.repository;

import com.tanghuan.jwt.BaseTest;
import com.tanghuan.jwt.entity.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transient
    public void insertUser() throws Exception {

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        users.add(user);

        user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("admin"));
        users.add(user);

        userRepository.save(users);

    }
}
