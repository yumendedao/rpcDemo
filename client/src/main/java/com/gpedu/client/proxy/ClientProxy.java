package com.gpedu.client.proxy;

import com.gpedu.proxy.RpcRequest;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 16:38
 **/
public class ClientProxy {

    private String host;
    private int port;

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object getProxy(Class<?> clazz, final String serviceName) {

        Class<?>[] interfaces = null;
        if (clazz.isInterface()) {
            interfaces = new Class[]{clazz};
        } else {
            interfaces = clazz.getInterfaces();
        }

        return Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try (Socket socket = new Socket(host, 8080)) {

                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

                        RpcRequest request = new RpcRequest();
                        request.setServiceName(serviceName);
                        request.setServiceClazz(proxy.getClass());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParamValues(args);

                        objectOutputStream.writeObject(request);

                        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
                            result = objectInputStream.readObject();
                        }
                    } finally {
                        socket.close();
                    }
                } finally {

                }
                return result;
            }
        });
    }

}
