package com.cache.demo.config.listener;

import org.springframework.stereotype.Component;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/11/6 17:44
 * @Description:
 */
@Component
public class MessageReceiver {
  public void receiveMessage(String message) {
    System.out.println("收到一条消息：" + message);
  }
}
