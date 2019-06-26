package com.gpedu;

import com.gpedu.server.proxy.ServiceProxy;
import com.gpedu.service.IUserService;
import com.gpedu.service.UserServieceImpl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 15:01
 **/
public class App {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, ClassNotFoundException {

        System.out.println("service start");
        ServiceProxy serviceProxy = new ServiceProxy();

        IUserService userService = new UserServieceImpl();

        ServiceProxy.publicService("userService", userService);

        serviceProxy.bindService(8080, userService);

    }
}
