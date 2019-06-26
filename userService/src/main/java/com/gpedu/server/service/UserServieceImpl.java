package com.gpedu.service;


import com.gpedu.model.User;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 14:55
 **/
public class UserServieceImpl implements IUserService{

    @Override
    public String sayHello(User user) {
        System.out.println("service sayHello:" + user);
        return user.toString();
    }

    @Override
    public String addUser(User user) {
        System.out.println("service addUser:" + user);
        return user.toString();
    }
}
