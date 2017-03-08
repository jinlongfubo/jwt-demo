package com.tanghuan.jwt.repository;

import com.tanghuan.jwt.entity.domain.User;

/**
 * Created by tanghuan on 2017/3/8.
 */
public interface UserRepository extends BaseRepository<User> {

    User findByUsername(String username);

}
