package com.cssf.service;

import com.cssf.mapper.RBookMapper;
import com.cssf.pojo.book.Count;
import com.cssf.pojo.book.Rbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 20:49
 * @Description: com.cssf.service
 * @Version: 1.0
 */
@Service
public class RbookServiceImp implements RbookService{
    @Autowired
    private RBookMapper rBookMapper;

    @Override
    public void insertRBook(Rbook rbook) {
        rBookMapper.insertRBook(rbook);
    }

    @Override
    public void rePayBook(Long userid, String bname, Long endtime) {
        rBookMapper.rePayBook(userid,bname,endtime);
    }

    @Override
    public List<Rbook> queryAllRbook() {
        return rBookMapper.queryAllRbook();
    }

    @Override
    public List<Rbook> queryAllPreRbook() {
        return rBookMapper.queryAllPreRbook();
    }

    @Override
    public List<Rbook> querybyUserName(String username) {
        return rBookMapper.querybyUserName(username);
    }

    @Override
    public List<Rbook> querybyBtime(Long btime) {
        return rBookMapper.querybyBtime(btime);
    }

    @Override
    public List<Rbook> querybyBookId(Long bookid) {
        return rBookMapper.querybyBookId(bookid);
    }

    @Override
    public List<Count> queryMaxByUser() {
        return rBookMapper.queryMaxByUser();
    }

    @Override
    public List<Count> queryMaxByBook() {
        return rBookMapper.queryMaxByBook();
    }
}
