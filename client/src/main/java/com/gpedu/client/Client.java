package com.gpedu.client;

import com.gpedu.client.proxy.ClientProxy;
import com.gpedu.model.User;
import com.gpedu.service.IUserService;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 16:35
 **/
public class Client {

    public static void main(String[] args) {

        ClientProxy proxy = new ClientProxy("localhost", 8080);

        IUserService userService = (IUserService) proxy.getProxy(IUserService.class, "userService");

        User user = new User();
        user.setName("ttt");
        user.setAge(18);
        String result = userService.sayHello(user);


        System.out.println(result);

    }
}
