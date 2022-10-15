package com.cssf.service;

import com.cssf.mapper.BookMapper;
import com.cssf.pojo.book.Book;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 17:24
 * @Description: com.cssf.service
 * @Version: 1.0
 */
@Service
public class BookServiceImp implements BookService{
    @Autowired
    BookMapper bookMapper;
    @Override
    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }

    @Override
    public void deleteBook(long id) {
        bookMapper.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public List<Book> queryAllBook() {
        List<Book> books = bookMapper.queryAllBook();

        return  books;
    }

    @Override
    public Book queryBookById(long id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public Book queryBookByName(String bname) {
        return bookMapper.queryBookByName(bname);
    }

    @Override
    public void changeBookState(String state) {
        bookMapper.changeBookState(state);
    }
}
