package com.cache.demo.im.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 11:26
 * @Description:
 */
public class NettyServer {
  public static void main(String[] args) {
    ServerBootstrap bootstrap = new ServerBootstrap();

    //server
    NioEventLoopGroup boss = new NioEventLoopGroup();
    //client
    NioEventLoopGroup worker = new NioEventLoopGroup();

    bootstrap.group(boss, worker)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<NioSocketChannel>() {
          @Override
          protected void initChannel(NioSocketChannel channel) throws Exception {
            System.out.println("服务端启动中");
            channel.pipeline().addLast(new FirstServerHandler());
          }
        })
        .bind(8000);


  }
}
