package com.gpedu.server.proxy;

import com.gpedu.proxy.RpcRequest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 15:01
 **/
public class ServiceProxy {

    private ThreadPoolExecutor executors;

    private static Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    public ServiceProxy() {
        executors = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public void bindService(int port, Object service) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try {
                    Socket socket = serverSocket.accept();

                    try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
                        RpcRequest request = (RpcRequest) objectInputStream.readObject();
                        Future<Object> future = executors.submit(new RequestHandle(request, service));
                        Object result = future.get();
                        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                            objectOutputStream.writeObject(result);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }


    }

    public static void publicService(String serviceName, Object service) {
        if (service != null) {
            serviceMap.put(serviceName, service);
        }
    }

    public static Object getService(String serviceName) {
        return serviceMap.get(serviceName);
    }

}
