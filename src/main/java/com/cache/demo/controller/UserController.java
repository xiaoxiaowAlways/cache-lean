package com.cache.demo.controller;

import com.cache.demo.model.User;
import com.cache.demo.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/10 10:16
 * @Description:
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {
  private UserService userService;

  @RequestMapping("/{id}")
  public User getUserById(@PathVariable("id") Integer userId) {
    log.info("getUserById... id:{}", userId);
    return userService.getUserById(userId);
  }

  @RequestMapping("/all/{type}")
  public List<User> getAllUser(@PathVariable("type") Integer type) {
    log.info("getAllUser...");
    List<User> allUser = userService.getAllUser(type);
    User user = allUser.get(0);
    System.out.println(user);
    return userService.getAllUser(type);
  }
}
