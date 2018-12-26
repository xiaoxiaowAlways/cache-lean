package com.cache.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/11/6 17:21
 * @Description: redis 消息订阅发布
 */
//@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("pub")
public class PubSubController {

  @Autowired
  StringRedisTemplate redisTemplate;
  private final String channel = "wolf:test";

  @RequestMapping("publish")
  public String publishMessage(String message) {
    redisTemplate.convertAndSend(channel, message);
    return "OK";
  }
}
