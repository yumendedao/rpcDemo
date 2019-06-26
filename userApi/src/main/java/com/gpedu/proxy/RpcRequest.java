package com.gpedu.proxy;

import java.io.Serializable;
import java.util.List;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 14:57
 **/
public class RpcRequest implements Serializable {

    private String serviceName;

    private Class<?> serviceClazz;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] paramValues;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Class<?> getServiceClazz() {
        return serviceClazz;
    }

    public void setServiceClazz(Class<?> serviceClazz) {
        this.serviceClazz = serviceClazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParamValues() {
        return paramValues;
    }

    public void setParamValues(Object[] paramValues) {
        this.paramValues = paramValues;
    }
}
