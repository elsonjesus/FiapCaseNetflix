package com.fiap.appcase.netflix.service;


import java.util.List;
import com.fiap.appcase.netflix.redis.Cache;

public interface CacheService {

    boolean saveUser(Cache user);

    List<Cache> fetchAllFilme();

    Cache fetchUserById(Long id);
    
    boolean deleteUser(Long id);

    boolean updateUser(Long id, Cache user);
}
