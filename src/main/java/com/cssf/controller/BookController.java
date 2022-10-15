package com.cssf.controller;

import com.alibaba.fastjson.JSONObject;
import com.cssf.pojo.ReMsg;
import com.cssf.pojo.book.Book;
import com.cssf.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 17:25
 * @Description: com.cssf.controller
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/3",produces = {"application/json;charset=UTF-8"})
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/update/{book}") //将书的info local name 设置
    public ReMsg setBookInfo(@PathVariable String book){
        Book rbook = JSONObject.parseObject(book, Book.class);
        bookService.updateBook(rbook);

        return new ReMsg("OK","500");
    }
    @GetMapping("/delete/{id}")
    public void deleteBookById(@PathVariable int id){
        bookService.deleteBook(id);
    }
    @GetMapping("/add/{book}")
    public void addBookInfo(@PathVariable String book){
        Book book1 = JSONObject.parseObject(book, Book.class);
        bookService.insertBook(book1);
    }
    @GetMapping("/queryAll/{pageNum}") //查书库
    public ReMsg queryAllBook(@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Book> books = bookService.queryAllBook();
        PageInfo<Book> tPageInfo = new PageInfo<Book>(books);

        return new ReMsg(tPageInfo,"500");
    }
    @GetMapping("/queryById/{id}") //查询图书状态 根据书号
    public ReMsg queryById(@PathVariable long id){
        if (bookService.queryBookById(id) == null){
            return new ReMsg("抱歉，ID不存在","404");
        }else {
            return new ReMsg(bookService.queryBookById(id),"500");
        }
    }
}
