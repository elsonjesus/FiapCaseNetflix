package com.fiap.appcase.netflix.repository;

import com.fiap.appcase.netflix.redis.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CacheRefImpl implements CacheRef {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "USER";

    @Override
    public boolean saveUser(Cache user) {
        try {
            redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Cache> fetchAllUser() {
        List<Cache> users;
        users = redisTemplate.opsForHash().values(KEY);
        return  users;
    }

    @Override
    public Cache fetchUserById(Long id) {
        Cache user;
        user = (Cache) redisTemplate.opsForHash().get(KEY,id.toString());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(Long id, Cache user) {
        try {
            redisTemplate.opsForHash().put(KEY, id, user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
