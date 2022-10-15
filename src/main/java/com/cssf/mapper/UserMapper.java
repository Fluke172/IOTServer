package com.cssf.mapper;

import com.cssf.pojo.book.BUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 18:08
 * @Description: com.cssf.mapper
 * @Version: 1.0
 */
@Mapper
public interface UserMapper {
    void insertUser(BUser bUser);

    void changeUserState(long id,String state);

    List<BUser> queryAllUser();

    BUser queryUserById(long id);

    BUser queryByUserName(String username);

    List<BUser> queryAll();
}
