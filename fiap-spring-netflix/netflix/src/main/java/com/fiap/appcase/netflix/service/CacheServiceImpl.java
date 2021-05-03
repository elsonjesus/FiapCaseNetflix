package com.fiap.appcase.netflix.service;

import com.fiap.appcase.netflix.redis.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.fiap.appcase.netflix.repository.CacheRef;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheRef cacheRef;

    @Override
    public boolean saveUser(Cache user) {
        return cacheRef.saveUser(user);
    }

    @Override
    public List<Cache> fetchAllFilme() {
        return cacheRef.fetchAllUser();
    }

    @Override
    public Cache fetchUserById(Long id) {
        return cacheRef.fetchUserById(id);
    }

    @Override
    public boolean deleteUser(Long id) {
        return cacheRef.deleteUser(id);
    }

    @Override
    public boolean updateUser(Long id, Cache user) {
        return cacheRef.updateUser(id,user);
    }

}
