package com.cssf.service;

import com.cssf.mapper.BicycleMapper;
import com.cssf.pojo.Bicycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/10/15 - 10 -15 - 19:17
 * @Description: com.cssf.service
 * @Version: 1.0
 */
@Service
public class BatteryService {
    @Autowired
    private BicycleMapper bicycleMapper;

    void updateBattery(long id,long bid){
        bicycleMapper.updateBattery(id,bid);
    }

    //修改开关
    void changeOpen(String isopen){
        bicycleMapper.changeOpen(isopen);
    }

    void insertBicycle(Bicycle bicycle){
        bicycleMapper.insertBicycle(bicycle);
    }

    List<Bicycle> queryAllBicycle(){
        return bicycleMapper.queryAllBicycle();
    }

    List<Bicycle> queryBicycleById(long id){
        return bicycleMapper.queryBicycleById(id);
    }
}
