package com.gpedu.server.proxy;

import com.gpedu.proxy.RpcRequest;
import com.gpedu.server.proxy.ServiceProxy;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 15:13
 **/
public class RequestHandle implements Callable<Object> {

    private RpcRequest rpcRequest;

    public RequestHandle(RpcRequest rpcRequest, Object service) {
        this.rpcRequest = rpcRequest;
    }


    @Override
    public Object call() throws Exception {
        String serviceName = rpcRequest.getServiceName();
        Object service = ServiceProxy.getService(serviceName);

        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
        Object result = method.invoke(service, rpcRequest.getParamValues());
        return result;
    }


}
