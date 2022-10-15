package com.cssf.mapper;

import com.cssf.pojo.book.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 17:15
 * @Description: com.cssf.mapper
 * @Version: 1.0
 */
@Mapper
public interface BookMapper {
    void insertBook(Book book);

    void deleteBook (long id);

    void updateBook(Book book);

    List<Book>  queryAllBook();

    Book queryBookById(long id);

    Book queryBookByName(String bname);

    void changeBookState(String state);



}
