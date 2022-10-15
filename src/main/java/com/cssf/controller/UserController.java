package com.cssf.controller;

import com.cssf.pojo.ReMsg;
import com.cssf.pojo.book.BUser;
import com.cssf.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 19:37
 * @Description: com.cssf.controller
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/4",produces = {"application/json;charset=UTF-8"})
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/changeState/{id}/{state}") //用户认证接口
    public String changeState(@PathVariable long id,@PathVariable String state){
        userService.changeUserState(id,state);
        return "ok";
    }
    @GetMapping("/queryAllUser/{pageNum}") //用户查询所有
    public ReMsg queryAllUser(@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<BUser> bUsers = userService.queryAllUser();

        PageInfo<BUser> bUserPageInfo = new PageInfo<>(bUsers, 10);
        return new ReMsg(bUserPageInfo,"500");
    }
    @GetMapping("/queryAll/{pageNum}") //用户查询所有
    public ReMsg queryAll(@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<BUser> bUsers = userService.queryAll();

        PageInfo<BUser> bUserPageInfo = new PageInfo<>(bUsers, 10);
        return new ReMsg(bUserPageInfo,"500");
    }
    @GetMapping("/queryById/{id}") //用户查询Byid
    public ReMsg queryByid(@PathVariable long id){
        return new ReMsg(userService.queryUserById(id),"500");
    }

}
