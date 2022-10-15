package com.cssf.service;

import com.cssf.pojo.book.BUser;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 19:31
 * @Description: com.cssf.service
 * @Version: 1.0
 */
public interface UserService {
    void insertUser(BUser bUser);

    void changeUserState(long id,String state);

    List<BUser> queryAllUser();

    BUser queryUserById(long id);

    BUser queryByUserName(String username);

    List<BUser> queryAll();
}
