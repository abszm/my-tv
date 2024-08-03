package com.lizongying.mytv0;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.ServerSocket;

object PortUtil {

    public int findFreePort() {
        int port = 36185;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            return port;
        } catch (IOException e) {
            System.out.println("端口[" + port + "]被占用，程序无法正常启动！");
            return -1;
        } finally {
            if (serverSocket!= null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    System.out.println("关闭端口[" + port + "]失败！");
                }
            }
        }
    }

    public String lan() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (Exception e) {
            System.out.println("获取IPv4地址失败！");
            return null;
        }
    }
}
