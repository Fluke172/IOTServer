package com.cssf.service;



import com.cssf.pojo.GenericBean;
import com.cssf.pojo.QueryCondition;
import com.cssf.pojo.QueryTime;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/7 - 09 -07 - 0:48
 * @Description: com.cssf.service
 * @Version: 1.0
 */
public interface GenericService {
    void insertData(GenericBean genericBean);
    //以下是温度
    PageInfo<GenericBean> queryAllTemp(int pageNum);

    List<GenericBean> queryRtimeTemp1();
    List<GenericBean> queryRtimeTemp2();

    List<GenericBean> queryByTimeTemp(QueryTime queryTime);



    void deleteTempById(int id);

    GenericBean updateTemp(GenericBean genericBean);


    //以下是湿度
    List<GenericBean> queryAllHum();

    List<GenericBean> queryRtimeHum1();
    List<GenericBean> queryRtimeHum2();

    List<GenericBean> queryByTimeHum(QueryTime queryTime);

    //红外
    GenericBean queryRtimeJing1();
    GenericBean queryRtimeJing2();

    //优雅的接口
    List<GenericBean> queryRtimeTemp(String param);
    List<GenericBean> queryRtimeHum(String param);

    List<GenericBean> querySensor(String param,String sensor,Long beginTime,Long endTime);
}
