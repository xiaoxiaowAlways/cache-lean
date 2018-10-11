package com.cache.demo.service;

import com.cache.demo.model.User;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/10 10:17
 * @Description:
 */
@Service
@Slf4j
public class UserService {

  @Cacheable(cacheNames = "user")
  public User getUserById(Integer userId) {
    log.info("getUserById 被调用。。。。");
    return new User(userId, "xiaoxiaow" + userId);
  }

  @Cacheable(cacheNames = "allUser")
  public List<User> getAllUser(Integer type) {
    log.info("getAllUser 被调用。。。。");
    User user1 = new User(1, "xiaoxiaow1");
    User user2 = new User(2, "xiaoxiaow2");
    return Arrays.asList(user1, user2);
  }
}
