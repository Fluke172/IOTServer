package com.cssf.controller;

import cn.hutool.json.JSONUtil;
import com.cssf.pojo.ReMsg;
import com.cssf.pojo.book.Count;
import com.cssf.pojo.book.Rbook;
import com.cssf.service.BookService;
import com.cssf.service.RbookService;
import com.cssf.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.Remote;
import java.util.Date;
import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 20:51
 * @Description: com.cssf.controller
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/5",produces = {"application/json;charset=UTF-8"})
public class RBookController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    RbookService rbookService;

    @GetMapping("/rentBook/{userId}/{bname}")  //借书接口
    public ReMsg rentBook(@PathVariable Long userId, @PathVariable String bname){
        if (userService.queryUserById(userId) == null){
            return new ReMsg("用户不存在","404");
        }else if (bookService.queryBookByName(bname) == null){
            return new ReMsg("书不存在","404");
        }else {
            int i = Integer.parseInt(bookService.queryBookByName(bname).getState());
            int j = ~i ;
            bookService.changeBookState(String.valueOf(j));//改变书本状态

            Date date = new Date();
            long time = date.getTime();
            rbookService.insertRBook(new Rbook(bname,userId,time,0));
            return new ReMsg("借书成功","500" );
        }

    }
    @GetMapping("/rePayBook/{userId}/{bname}") //还书接口
    public ReMsg rePayBook(@PathVariable Long userId, @PathVariable String bname) {
        if (userService.queryUserById(userId) == null){
            return new ReMsg("用户不存在","404");
        }else if (bookService.queryBookByName(bname) == null){
            return new ReMsg("书不存在","404");
        }else {
            int i = Integer.parseInt(bookService.queryBookByName(bname).getState());
            int j = ~i ;
            bookService.changeBookState(String.valueOf(j));


            Date date = new Date();
            long time = date.getTime();
            rbookService.rePayBook(userId,bname,time);
            return new ReMsg("还书成功","500");
        }
    }
    @GetMapping("/queryAll/{pageNum}") //借书历史记录
    public ReMsg queryAll(@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Rbook> rbooks = rbookService.queryAllRbook();
        return new ReMsg(new PageInfo<>(rbooks,10),"500");
    }
    @GetMapping("/queryAllPre/{pageNum}") //当前任在借 接口
    public ReMsg queryAllPre(@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Rbook> rbooks = rbookService.queryAllPreRbook();
        return new ReMsg(new PageInfo<>(rbooks,10),"500");
    }
    @GetMapping("/queryByUserName/{userName}/{pageNum}")  //根据借阅人来查询
    public ReMsg queryByUserName(@PathVariable String userName, @PathVariable int pageNum){
        if (userService.queryByUserName(userName) == null){
            return new ReMsg("empty",JSONUtil.toJsonStr( "用户不存在"));
        }else {
            PageHelper.startPage(pageNum,10);

            PageInfo<Rbook> rbookPageInfo = new PageInfo<>(rbookService.querybyUserName(userName), 10);

            return new ReMsg(rbookPageInfo,"500");
        }
    }
    @GetMapping("/queryByBtime/{btime}/{pageNum}")  //根据时间段来查询
    public ReMsg queryByBtime(@PathVariable Long btime, @PathVariable int pageNum){
        if (rbookService.querybyBtime(btime) == null){
            return new ReMsg("empty",JSONUtil.toJsonStr( "该时间段没有人借书"));
        }else {
            PageHelper.startPage(pageNum,10);

            PageInfo<Rbook> rbookPageInfo = new PageInfo<>(rbookService.querybyBtime(btime), 10);

            return new ReMsg(rbookPageInfo,"500");
        }
    }
    @GetMapping("/queryByBookId/{bookid}/{pageNum}")  //根据时间段来查询
    public ReMsg queryByBookid(@PathVariable Long bookid, @PathVariable int pageNum){
        if (rbookService.querybyBookId(bookid) == null){
            return new ReMsg("empty",JSONUtil.toJsonStr( "该时间段没有人借书"));
        }else {
            PageHelper.startPage(pageNum,10);

            PageInfo<Rbook> rbookPageInfo = new PageInfo<>(rbookService.querybyBookId(bookid), 10);

            return new ReMsg(rbookPageInfo,"500");
        }
    }
    @GetMapping("/queryMaxByUser")  //查找借阅次数最多
    public ReMsg queryMaxByUser(){
        List<Count> counts = rbookService.queryMaxByUser();
        for (Count count : counts) {
            count.setData(userService.queryUserById(Long.parseLong(count.getData())).getName());
        }


        return new ReMsg(counts,"500");
    }
    @GetMapping("/queryMaxByBook")  //查找借阅次数最多的书
    public ReMsg queryMaxByBook(){
        List<Count> counts = rbookService.queryMaxByBook();


        return new ReMsg(counts,"500");
    }
    }
