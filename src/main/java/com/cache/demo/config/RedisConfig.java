package com.cache.demo.config;

import com.cache.demo.model.User;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/10 10:25
 * @Description:
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<Object, User> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, User> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(User.class));
    return template;
  }

  @Primary
  @Bean
  public RedisCacheManager userCacheManager(RedisTemplate<Object, User> userRedisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(userRedisTemplate);
    cacheManager.setUsePrefix(true);
    return cacheManager;
  }

  @Bean
  public RedisTemplate<Object, Object> userListRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    serializer.setObjectMapper(objectMapper);
    template.setDefaultSerializer(serializer);
    return template;
  }

  @Bean
  public RedisCacheManager userListCacheManager(RedisTemplate<Object, Object> userListRedisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(userListRedisTemplate);
    cacheManager.setUsePrefix(true);
    return cacheManager;
  }


}
