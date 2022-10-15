package com.cssf.service;

import com.cssf.mapper.UserMapper;
import com.cssf.pojo.book.BUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 19:32
 * @Description: com.cssf.service
 * @Version: 1.0
 */
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insertUser(BUser bUser) {
        userMapper.insertUser(bUser);
    }

    @Override
    public void changeUserState(long id,String state) {
        userMapper.changeUserState(id,state);
    }

    @Override
    public List<BUser> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public BUser queryUserById(long id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public BUser queryByUserName(String username) {
        return userMapper.queryByUserName(username);
    }

    @Override
    public List<BUser> queryAll() {
        return userMapper.queryAll();
    }
}
