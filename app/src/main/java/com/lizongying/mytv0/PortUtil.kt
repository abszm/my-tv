package com.lizongying.mytv0

import java.io.IOException
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.ServerSocket

object PortUtil {

    fun findFixedPort(): Int {
        val fixedPort = 36185
        var port = -1
        try {
            // 使用指定端口创建 ServerSocket
            ServerSocket(fixedPort).use { socket ->
                port = socket.localPort
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return port
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
        return null
    }
}