package com.cssf.service;



import com.cssf.mapper.GenericMapper;
import com.cssf.pojo.GenericBean;
import com.cssf.pojo.QueryCondition;
import com.cssf.pojo.QueryTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/7 - 09 -07 - 0:49
 * @Description: com.cssf.service
 * @Version: 1.0
 */
@Service
public class GenericServiceImp implements GenericService {
    @Autowired
    private GenericMapper genericMapper;

    @Override
    public void insertData(GenericBean genericBean) {
        genericMapper.insertData(genericBean);
    }

    @Override
    public PageInfo<GenericBean> queryAllTemp(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<GenericBean> genericBeans = genericMapper.queryAllTemp();


        PageInfo<GenericBean> of = new PageInfo<>(genericBeans, 10);

        System.out.println(of);
        return of;

    }

    @Override
    public List<GenericBean> queryRtimeTemp1() {
        return genericMapper.queryRtimeTemp1();
    }
    @Override
    public List<GenericBean> queryRtimeTemp2() {
        return genericMapper.queryRtimeTemp2();
    }

    @Override
    public List<GenericBean> queryByTimeTemp(QueryTime queryTime) {
        return genericMapper.queryByTimeTemp(queryTime);
    }



    @Override
    public void deleteTempById(int id) {
        genericMapper.deleteTempById(id);
    }

    @Override
    public GenericBean updateTemp(GenericBean genericBean) {
        return genericMapper.updateTemp(genericBean);
    }



    @Override
    public List<GenericBean> queryAllHum() {
        return genericMapper.queryAllHum();
    }

    @Override
    public List<GenericBean> queryRtimeHum1() {
        return genericMapper.queryRtimeHum1();
    }
    @Override
    public List<GenericBean> queryRtimeHum2() {
        return genericMapper.queryRtimeHum2();
    }

    @Override
    public List<GenericBean> queryByTimeHum(QueryTime queryTime) {
        return genericMapper.queryByTimeHum(queryTime);
    }

    @Override
    public GenericBean queryRtimeJing1() {
        return genericMapper.queryRtimeJing1();
    }
    @Override
    public GenericBean queryRtimeJing2() {
        return genericMapper.queryRtimeJing2();
    }

    @Override
    public List<GenericBean> queryRtimeTemp(String param) {
        QueryCondition queryCondition = new QueryCondition("and name = \"" + param + "\"");

        return genericMapper.queryRtimeTemp(queryCondition);


    }

    @Override
    public List<GenericBean> queryRtimeHum(String param) {
        QueryCondition queryCondition = new QueryCondition("and name = \"" + param + "\"");

        return genericMapper.queryRtimeHum(queryCondition);
}

    @Override
    public List<GenericBean> querySensor(String param, String sensor, Long beginTime, Long endTime) {
        QueryCondition queryCondition = new QueryCondition(" datakey = \"" + sensor + "\" and name = \"" + param + "\" and revtime between \"" + beginTime + "\" and \"" + endTime+ "\"");
        return genericMapper.querySensor(queryCondition);
    }

}


