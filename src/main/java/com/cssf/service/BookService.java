package com.cssf.service;

import com.cssf.pojo.book.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 17:24
 * @Description: com.cssf.service
 * @Version: 1.0
 */
public interface BookService {
    void insertBook(Book book);

    void deleteBook (long id);

    void updateBook(Book book);

    List<Book> queryAllBook();

    Book queryBookById(long id);
    Book queryBookByName(String bname);

    void changeBookState(String state);

}
