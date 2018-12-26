package com.cache.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Will Wang 3
 * @Date: 2018/12/26 15:50
 * @Description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/help")
@Slf4j
public class HelpController {
  @RequestMapping("/test")
  public String test() {
    log.info("sssssss");
    return "test";
  }
}
