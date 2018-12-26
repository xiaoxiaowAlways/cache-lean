package com.cache.demo.config;

import com.cache.demo.config.listener.MessageReceiver;
import com.cache.demo.config.listener.TopicListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/10 10:25
 * @Description:
 */
//@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
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
  public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    cacheManager.setUsePrefix(true);
    return cacheManager;
  }

  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                          TopicListener topicListener) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    //订阅了一个叫chat 的通道
    container.addMessageListener(topicListener, new PatternTopic("wolf:test"));
    //这个container 可以添加多个 messageListener
    return container;
  }

  /*@Bean
  MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
    //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
    //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }*/
}
