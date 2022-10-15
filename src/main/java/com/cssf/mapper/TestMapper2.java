package com.cssf.mapper;



import com.cssf.pojo.QueryTime;
import com.cssf.pojo.TestBean2;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/25 - 05 -25 - 9:27
 * @Description: com.cssf.mapper
 * @Version: 1.0
 */
@Mapper
public interface TestMapper2 {
    void insertData(TestBean2 testBean);

    Page<TestBean2> queryAll();

    List<TestBean2> queryRtimeData();
    TestBean2 queryRtimeData1();

    TestBean2 queryRtimeData2();


    Page<TestBean2> queryByTime(QueryTime queryTime);

    void deleteTestBean(int id);

    TestBean2 updateTestBean(TestBean2 testBean2);

}
