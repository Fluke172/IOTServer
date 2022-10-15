package com.cssf.mapper;



import com.cssf.pojo.GenericBean;
import com.cssf.pojo.QueryCondition;
import com.cssf.pojo.QueryTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/7 - 09 -07 - 0:43
 * @Description: com.cssf.mapper
 * @Version: 1.0
 */
@Mapper
public interface GenericMapper {
    //泛型类

    void insertData(GenericBean genericBean);

    void deleteTempById(int id);

    GenericBean updateTemp(GenericBean genericBean);
    //以下是温度
    List<GenericBean> queryAllTemp();

    List<GenericBean> queryRtimeTemp1();
    List<GenericBean> queryRtimeTemp2();
    List<GenericBean> queryByTimeTemp(QueryTime queryTime);

    //以下是湿度
    List<GenericBean> queryAllHum();

    List<GenericBean> queryRtimeHum1();
    List<GenericBean> queryRtimeHum2();

    List<GenericBean> queryByTimeHum(QueryTime queryTime);

    //红外

    GenericBean queryRtimeJing1();
    GenericBean queryRtimeJing2();

    //优雅的接口~~~
    List<GenericBean> queryRtimeTemp(QueryCondition queryCondition);
    List<GenericBean> queryRtimeHum(QueryCondition queryCondition);

    //根据传感器和起止时间查

    List<GenericBean> querySensor(QueryCondition queryCondition);
}
