package com.cache.demo.im.simple;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @Auther: Will Wang 3
 * @Date: 2018/10/15 10:30
 * @Description:
 */
public class IOServer {


  public static void main(String[] args) throws Exception {
    IOServer server = new IOServer();
//    server.serverTest();
    server.clientTest();
  }

  @Test
  public void serverTest() throws Exception {
    ServerSocket serverSocket = new ServerSocket(8000);
    new Thread(() -> {
      while (true) {
        try {
          Socket socket = serverSocket.accept();
          new Thread(() -> {
            int len;
            byte[] data = new byte[1024];
            try {
              InputStream inputStream = socket.getInputStream();
              while ((len = inputStream.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }).start();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }).start();

  }

  @Test
  public void clientTest() throws Exception {
    new Thread(() -> {
      try {
        Socket socket = new Socket("127.0.0.1", 8000);
        while (true) {
          socket.getOutputStream().write((new Date() + ": Hello world").getBytes());
          Thread.sleep(2000);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
