package com.tanghuan.jwt.repository;

import com.tanghuan.jwt.BaseTest;
import com.tanghuan.jwt.entity.domain.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */
public class RoleRepositoryTest extends BaseTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transient
    public void insertRole() throws Exception {

        List<Role> roles = new ArrayList<>();

        roles.add(new Role("ADMIN"));
        roles.add(new Role("USER"));

        roleRepository.save(roles);
    }
}
