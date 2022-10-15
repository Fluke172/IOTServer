package com.cssf.mapper;

import com.cssf.pojo.Bicycle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/10/5 - 10 -05 - 14:40
 * @Description: com.cssf.mapper
 * @Version: 1.0
 */
@Mapper
public interface BicycleMapper {
    //修改电池
    void updateBattery(long id,long bid);

    //修改开关
    void changeOpen(String isopen);

    void insertBicycle(Bicycle bicycle);

    List<Bicycle> queryAllBicycle();

    List<Bicycle> queryBicycleById(long id);
}
