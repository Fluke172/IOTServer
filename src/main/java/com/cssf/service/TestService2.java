package com.cssf.service;


import com.cssf.pojo.TestBean2;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/7 - 09 -07 - 11:27
 * @Description: com.cssf.service
 * @Version: 1.0
 */
public interface TestService2 {
    void insertData(TestBean2 testBean2);

    Page<TestBean2> queryAll(int pageNum);

    List<TestBean2> queryRtimeData();



    Page<TestBean2> queryByTime(int pageNum,long beginTime, long endTime);

    void deleteTestBean(int id);

    TestBean2 updateTestBean(TestBean2 testBean2);
}
