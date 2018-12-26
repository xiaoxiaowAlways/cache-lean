package com.cache.demo.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Will Wang 3
 * @Date: 2018/12/26 15:13
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

  /**
   * 切面点
   */
  private final String POINT_CUT = "execution(public * com..controller..*(..))";

  @Pointcut(POINT_CUT)
  private void pointcut() {
  }

  @Before(POINT_CUT)
  public void before(JoinPoint point) {
    Object[] args = point.getArgs();

    Signature signature = point.getSignature();
    String name = signature.getName();
    String simpleName = signature.getDeclaringType().getSimpleName();

    log.info("{}.{}({}) start...", simpleName, name, args.length == 0 ? "" : Arrays.asList(args));

  }

}
