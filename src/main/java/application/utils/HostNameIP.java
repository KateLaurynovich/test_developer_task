package application.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostNameIP {
    public static InetAddress getIp() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getHostName() {
        InetAddress ip = getIp();
        return ip.getHostName();
    }
}
