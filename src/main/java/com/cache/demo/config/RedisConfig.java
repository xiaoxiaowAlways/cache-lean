package com.cache.demo.config;

import com.cache.demo.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

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

  @Bean
  public RedisCacheManager userCacheManager(RedisTemplate<Object, User> userRedisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(userRedisTemplate);
    cacheManager.setUsePrefix(true);
    return cacheManager;
  }
}
