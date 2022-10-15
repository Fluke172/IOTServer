package com.cssf.service;


import com.cssf.mapper.TestMapper2;
import com.cssf.pojo.QueryTime;
import com.cssf.pojo.TestBean2;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/25 - 05 -25 - 9:38
 * @Description: com.cssf.service
 * @Version: 1.0
 */
@Service
public class TestServiceImp2 implements TestService2{
    @Autowired
    private TestMapper2 testMapper;


    @Override
    public void insertData(TestBean2 testBean2) {
        testMapper.insertData(testBean2);
    }

    @Override
    public Page<TestBean2> queryAll(int pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<TestBean2> list = testMapper.queryAll();


//        PageInfo<TestBean2> testBean2PageInfo = new PageInfo<>(list);

//        List<TestBean2> list1 = testBean2PageInfo.getList();

        return list;

    }
    @Override
    public List<TestBean2> queryRtimeData(){
        return  testMapper.queryRtimeData();
    }

    @Override
    public Page<TestBean2> queryByTime(int pageNum,long beginTime, long endTime) {
        QueryTime queryTime = new QueryTime(beginTime, endTime);
        PageHelper.startPage(pageNum,10);
        Page<TestBean2> list = testMapper.queryByTime(queryTime);

        return list;

    }

    @Override
    public void deleteTestBean(int id) {
        testMapper.deleteTestBean(id);
    }

    @Override
    public TestBean2 updateTestBean(TestBean2 testBean2) {
        return testMapper.updateTestBean(testBean2);
    }


}
