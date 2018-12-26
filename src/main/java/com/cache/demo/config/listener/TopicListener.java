package com.cache.demo.config.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/11/6 17:24
 * @Description:
 */
@Component
@AllArgsConstructor
public class TopicListener implements MessageListener {
  private StringRedisTemplate redisTemplate;

  @Override
  public void onMessage(Message message, byte[] pattern) {
    byte[] body = message.getBody();//请使用valueSerializer
    byte[] channel = message.getChannel();
    String itemValue = (String) redisTemplate.getValueSerializer().deserialize(body);
    String topic = redisTemplate.getStringSerializer().deserialize(channel);

    System.out.println(itemValue + " ----- " + topic);
  }
}
