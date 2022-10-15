package com.cssf.controller;


import com.cssf.pojo.GenericBean;
import com.cssf.pojo.QueryTime;
import com.cssf.pojo.ReMsg;
import com.cssf.redis.RedisUtils;
import com.cssf.service.GenericService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/7 - 09 -07 - 0:51
 * @Description: com.cssf.controller
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/2")
public class GenericController {

    @Autowired
    private GenericService genericService;

    @Autowired
    private RedisUtils redisUtils;

    //记录开关情况 防断电 每进行一次修改就调用一次
    @GetMapping("/record/{state}")
    public ReMsg recordState(@PathVariable String state){
        redisUtils.set("switchState",state);
        return new ReMsg("状态保持成功","500");
    }

    //每次打开网页访问一次
    @GetMapping("/obtain")
    public ReMsg obtainState(@PathVariable String state){
        String switchState = (String) redisUtils.get("switchState");
        return new ReMsg(switchState,"500");
    }


    //以下是温度
    @GetMapping("/allTemp/{pageNum}")
    public PageInfo<GenericBean> findAllTemp(@PathVariable int pageNum){
        return genericService.queryAllTemp(pageNum);
    }

    @GetMapping("/RTemp1")
    public List<GenericBean> findRtimeTemp1(){
        return genericService.queryRtimeTemp1();
    }
    @GetMapping("/RTemp2")
    public List<GenericBean> findRtimeTemp2(){
        return genericService.queryRtimeTemp2();
    }
    @GetMapping("/TimeTemp/{pageNum}/{beginTime}/{endTime}")
    public PageInfo<GenericBean> findRtimeTempByTime(@PathVariable int pageNum,@PathVariable long beginTime,@PathVariable long endTime){
        PageHelper.startPage(pageNum,10);

        List<GenericBean> genericBeans = genericService.queryByTimeTemp(new QueryTime(beginTime, endTime));

        PageInfo<GenericBean> genericBeanPageInfo = new PageInfo<>(genericBeans, 10);

        return genericBeanPageInfo;
    }
    @DeleteMapping("/deleteTemp/{id}")
    void deleteTemp(@PathVariable int id){
        genericService.deleteTempById(id);
    }
    @PutMapping("/updateTemp/{genericBean}")
    void updateTemp(@PathVariable GenericBean genericBean){
        genericService.updateTemp(genericBean);
    }


    //以下是湿度
    @GetMapping("/allHum/{pageNum}")
    public PageInfo<GenericBean> findAllHum(@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<GenericBean> genericBeans = genericService.queryAllHum();
        PageInfo<GenericBean> genericBeanPageInfo = new PageInfo<>(genericBeans, 10);

        return genericBeanPageInfo;
    }

    @GetMapping("/RHum1")
    public List<GenericBean> findRtimeHum1(){
        return genericService.queryRtimeHum1();
    }
    @GetMapping("/RHum2")
    public List<GenericBean> findRtimeHum2(){
        return genericService.queryRtimeHum2();
    }
    @GetMapping("/TimeHum/{pageNum}/{beginTime}/{endTime}")
    public PageInfo<GenericBean> findRtimeHumByTime(@PathVariable int pageNum,@PathVariable long beginTime,@PathVariable long endTime){
        PageHelper.startPage(pageNum,10);

        List<GenericBean> genericBeans = genericService.queryByTimeHum(new QueryTime(beginTime, endTime));

        PageInfo<GenericBean> genericBeanPageInfo = new PageInfo<>(genericBeans, 10);

        return genericBeanPageInfo;
    }

    //新接口 更加优雅
    @GetMapping("/RTemp/{param}")
    public List<GenericBean> findRtimeTemp(@PathVariable String param){
        return genericService.queryRtimeTemp(param);
    }

    @GetMapping("/RHum/{param}")
   public List<GenericBean> findRtimeHum(@PathVariable String param){
        return genericService.queryRtimeHum(param);
    }


    //传感器类型 起止时间
    @GetMapping("/querySensor/{param}/{sensor}/{beginTime}/{endTime}/{pageNum}")
    public PageInfo<GenericBean> querySensor(@PathVariable String param,@PathVariable String sensor,@PathVariable Long beginTime,@PathVariable Long endTime,@PathVariable int pageNum){
        PageHelper.startPage(pageNum,10);
        List<GenericBean> genericBeans = genericService.querySensor(param, sensor, beginTime, endTime);
        PageInfo<GenericBean> genericBeanPageInfo = new PageInfo<>(genericBeans, 10);
        return genericBeanPageInfo;
    }

    @GetMapping("/querySensorNoPage/{param}/{sensor}/{beginTime}/{endTime}")
    public List<GenericBean> querySensorNoPage(@PathVariable String param,@PathVariable String sensor,@PathVariable Long beginTime,@PathVariable Long endTime){

        List<GenericBean> genericBeans = genericService.querySensor(param, sensor, beginTime, endTime);

        return genericBeans;
    }



}
