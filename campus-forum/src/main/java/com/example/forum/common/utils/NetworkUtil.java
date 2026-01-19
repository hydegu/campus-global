package com.example.forum.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

@Slf4j
public class NetworkUtil {

    private static String localIp;

    static {
        initLocalIp();
    }

    private static void initLocalIp() {
        try {
            localIp = getLocalIpAddress();
            log.info("本机IP地址初始化成功: {}", localIp);
        } catch (Exception e) {
            log.error("获取本机IP地址失败，将使用localhost", e);
            localIp = "localhost";
        }
    }

    public static String getLocalIpAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            
            if (localHost.isLoopbackAddress()) {
                return getNonLoopbackIpAddress();
            }
            
            String ip = localHost.getHostAddress();
            
            if (!isLocalHostIp(ip)) {
                return ip;
            }
            
            return getNonLoopbackIpAddress();
        } catch (UnknownHostException e) {
            log.error("获取本地主机地址失败", e);
            return getNonLoopbackIpAddress();
        }
    }

    private static String getNonLoopbackIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    
                    if (!address.isLoopbackAddress() && address.getHostAddress() != null) {
                        String ip = address.getHostAddress();
                        
                        if (!ip.contains(":") && !isLocalHostIp(ip)) {
                            log.info("找到非回环IP地址: {} (接口: {})", ip, networkInterface.getName());
                            return ip;
                        }
                    }
                }
            }
            
            log.warn("未找到合适的非回环IP地址，使用localhost");
            return "localhost";
            
        } catch (SocketException e) {
            log.error("获取网络接口失败", e);
            return "localhost";
        }
    }

    private static boolean isLocalHostIp(String ip) {
        return "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip);
    }

    public static String getLocalIp() {
        return localIp;
    }

    public static String getServerUrl(int port) {
        return "http://" + localIp + ":" + port;
    }
}
