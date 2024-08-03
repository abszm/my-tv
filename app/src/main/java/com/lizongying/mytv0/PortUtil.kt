package com.lizongying.mytv0

import java.io.IOException
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.ServerSocket
import java.preferredPort

object PortUtil {

    fun findFreePort(): Int {
        val preferredPort = 36185
        var serverSocket: ServerSocket? = null
        try {
            // 尝试使用首选端口创建ServerSocket
            serverSocket = ServerSocket(preferredPort)
            // 如果创建成功，说明端口可用，返回该端口号
            return preferredPort
        } catch (e: IOException) {
            // 如果首选端口被占用，则捕获异常并继续
        }

        // 如果首选端口不可用，则让系统为我们选择一个随机端口
        try {
            ServerSocket(0).use { socket ->
                // 返回系统分配的随机端口号
                return socket.localPort
            }
        } catch (e: IOException) {
            // 如果连系统分配的随机端口都无法创建（理论上非常罕见），则抛出异常
            throw IOException("Unable to find a free port", e)
        }

        // 注意：由于上面的catch块已经处理了所有可能的异常情况，
        // 因此这个return语句实际上是不会被执行到的，但为了代码的完整性，我还是保留了它。
        // 在实际情况中，您可能想要移除这个return语句或将其替换为其他逻辑。
        return -1 // 这行代码实际上不会被执行到
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