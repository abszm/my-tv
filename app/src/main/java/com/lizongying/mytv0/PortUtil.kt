package com.lizongying.mytv0;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.ServerSocket;

object PortUtil {

    fun findFreePort(): Int {
        var port = 36186
        try {
            ServerSocket(port).use { socket ->
                // 如果成功绑定到端口，返回该端口
            }
        } catch (e: IOException) {
            e.printStackTrace()
            // 如果绑定失败，返回-1
            return -1
        }
        return port
    }
}

    fun lan(): String? {
        val networkInterfaces = NetworkInterface.getNetworkInterfaces()
        while (networkInterfaces.hasMoreElements()) {
            val inetAddresses = networkInterfaces.nextElement().inetAddresses
            while (inetAddresses.hasMoreElements()) {
                val inetAddress = inetAddresses.nextElement()
                if (inetAddress is Inet4Address) {
                    if (inetAddress.hostAddress == "127.0.0.1") {
                        continue
                    }
                    return inetAddress.hostAddress
                }
            }
        }
