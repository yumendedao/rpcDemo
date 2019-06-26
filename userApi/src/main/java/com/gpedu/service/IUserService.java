package com.gpedu.service;

import com.gpedu.model.User;

/**
 * @Description description
 * @Author xcgu
 * @Data 2019-06-26 14:48
 **/
public interface IUserService {

    String sayHello(User user);

    String addUser(User user);
}
