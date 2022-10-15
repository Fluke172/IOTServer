package com.cssf.service;

import com.cssf.pojo.book.Count;
import com.cssf.pojo.book.Rbook;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 20:48
 * @Description: com.cssf.service
 * @Version: 1.0
 */
public interface RbookService {
    void insertRBook(Rbook rbook);

    void rePayBook(Long userid,String bname,Long endtime);

    List<Rbook> queryAllRbook();
    List<Rbook> queryAllPreRbook();

    List<Rbook> querybyUserName(String username);

    List<Rbook> querybyBtime(Long btime);
    List<Rbook> querybyBookId(Long bookid);

    List<Count> queryMaxByUser();
    List<Count> queryMaxByBook();
}
