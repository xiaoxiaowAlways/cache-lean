//package com.cache.demo.config.serializer;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
///**
// * @Auther: Will Wang 3
// * @Date: 2018/10/10 09:48
// * @Description: 自定义redis序列化器
// */
//public class FastJsonSerializer<T> implements RedisSerializer<T> {
//  @Override
//  public byte[] serialize(T t) throws SerializationException {
//    return JSON.toJSONString(t).getBytes();
//  }
//
//  @Override
//  public T deserialize(byte[] bytes) throws SerializationException {
//    String json = new String(bytes);
//    return (T) JSONObject.parse(json);
//  }
//}
