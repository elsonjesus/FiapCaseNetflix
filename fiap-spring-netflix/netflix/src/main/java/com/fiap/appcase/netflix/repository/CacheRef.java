package com.fiap.appcase.netflix.repository;

import com.fiap.appcase.netflix.redis.Cache;

import java.util.List;

public interface CacheRef {
    boolean saveUser(Cache user);

    List<Cache> fetchAllUser();

    Cache fetchUserById(Long id);

    boolean deleteUser(Long id);

    boolean updateUser(Long id, Cache user);
}
