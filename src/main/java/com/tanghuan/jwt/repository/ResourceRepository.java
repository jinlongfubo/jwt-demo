package com.tanghuan.jwt.repository;

import com.tanghuan.jwt.entity.domain.Resource;

import java.util.List;

/**
 * Created by Arthur on 2017/3/14.
 */
public interface ResourceRepository extends BaseRepository<Resource> {

    List<Resource> findByEnabledTrue();

}
