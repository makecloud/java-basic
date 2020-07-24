package com.liuyihui.common.net.socket;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class SocketDemo1 {
    private final int LOCAL_SOCKET_PORT = 63203;
    private final int DESTINATION_PORT = 28780;
    // private final String BROADCAST_IP = "255.255.255.255";
    private final String TARGET_HOST_IP = "192.168.1.157";
    private final String BROADCAST_IP = "192.168.0.255";
    // private final String NET_GROUP_ADDRESS = "224.0.0.2";
    private DatagramSocket datagramSocket = null;


    @Test
    public void testSend() {

        Thread sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress destinationInetAddress;
                    DatagramSocket skt = null;
                    DatagramPacket dataPkt;
                    try {
                        //构建socket服务
                        skt = new DatagramSocket(DESTINATION_PORT);
                        //skt.setBroadcast(true);
                        //skt.setSoTimeout(3000);

                        System.out.println(String.format("local address %s:%s", skt.getLocalAddress().getHostName(), skt.getPort()));

                        //数据包目标ip
                        destinationInetAddress = InetAddress.getByName(TARGET_HOST_IP);

                        //cycle send
                        while (true) {
                            long systemTime = System.currentTimeMillis();
                            byte[] bytes = long2Bytes(systemTime);
                            dataPkt = new DatagramPacket(bytes, bytes.length, destinationInetAddress, DESTINATION_PORT);
                            skt.send(dataPkt);
                            System.out.println("send:" + systemTime);
                            //3s发送一次数据包
                            Thread.sleep(1000 * 3);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (skt != null) {
                            skt.close();
                        }
                    }
                    System.out.println(" send run to exit");
                } catch (InterruptedException e) {
                    System.out.println(" send interrupted exit");
                    e.printStackTrace();
                }
            }
        });
        sendThread.start();

        //运行线程一直等待
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void receiveDatagram() {

        Thread receiveThread = new Thread(() -> {
            //开启socket接收
            byte[] bytes = new byte[8];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            try {
                datagramSocket = new DatagramSocket(DESTINATION_PORT);
//            datagramSocket.setBroadcast(true);
                while (true) {
                    System.out.println("receive Datagram Packet...");
                    datagramSocket.receive(datagramPacket);
                    byte[] bytes1 = datagramPacket.getData();
                    long time = bytes2Long(bytes1);
                    System.out.println("receiveDatagramPacket: " + time);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
            }
            System.out.println(" receive thread run to exit");
        });
        receiveThread.start();

        //
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("" + receiveThread.isAlive());
            System.out.println("" + receiveThread.isInterrupted());

            if (datagramSocket != null) {
                datagramSocket.close();
            }

            System.out.println("do close");

            System.out.println("" + receiveThread.isAlive());
            System.out.println("" + receiveThread.isInterrupted());

        }).start();


        //运行线程一直等待
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e.toString());
        }
        return "";
    }


    public static byte[] long2Bytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        return byteNum;
    }

    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }
}
