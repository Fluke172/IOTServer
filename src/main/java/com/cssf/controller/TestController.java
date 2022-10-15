package com.cssf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.cssf.netty.inbound.MyJsonServer;
import com.cssf.pojo.TestBean2;
import com.cssf.pojo.disused.*;
import com.cssf.service.TestService2;
import com.cssf.websocket.WebSocket;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/25 - 05 -25 - 9:40
 * @Description: com.cssf.controller
 * @Version: 1.0
 */
@CrossOrigin
@RestController
//@RequestMapping(value = "/1",produces = {"application/json;charset=UTF-8"}) //这个注解可以解决前端返回中文乱码问题
public class TestController {

    @Autowired
    private WebSocket webSocket;
    @Autowired
    private TestService2 testService2;
    @RequestMapping("/queryAll")
    public String queryAll(@RequestParam("pageNum") int pageNum){
        Page<TestBean2> testBean2s = testService2.queryAll(pageNum);
        System.out.println(testBean2s.getTotal());
        String s = JSON.toJSONString(testBean2s);



        return JSON.toJSONString(new Msg(s,testBean2s.getTotal()));
    }
    @RequestMapping("/queryBytime")
    public Page<TestBean2> queryByTime(@RequestParam("pageNum") int pageNum,@RequestParam("beginTime") Long beginTime,@RequestParam("endTime") Long endTime){
        Page<TestBean2> testBean2s = testService2.queryByTime(pageNum,beginTime,endTime);

        return testBean2s;
    }
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
//    @RequestMapping("/isOpen")
//    public boolean isOpen(){
//        //这里查询数据库里面的那些灯之类的是否打开
//        return false;
//    }
    @RequestMapping("/changeOpen")
    public String changeOpen(@RequestParam("is") Boolean isopen){
        MyJsonServer.users.forEach(ch ->{
            ch.writeAndFlush("hello");
        });
        return "hello";
    }
    @RequestMapping("/queryRtime")
    public List<TestBean2> queryRtime(){
        return testService2.queryRtimeData();
    }

    @RequestMapping("/yanWu1")
    public Msg queryYanwu1(@RequestParam("pageNum") int pageNum){
        Page<TestBean2> testBean2s = testService2.queryAll(pageNum);
        PageHelper.startPage(pageNum,10);
        Page<YanWu1> yanWu1s = new Page<>() ;
        for (TestBean2 testBean2 : testBean2s) {
            String data = testBean2.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String yanWu1 = jsonObject.getString("YanWu1");
            yanWu1s.add(new YanWu1(yanWu1,"烟雾传感器1",testBean2.revtime));

        }
        String s = JSON.toJSONString(yanWu1s);

        return new Msg(s,testBean2s.getTotal());
    }
    @RequestMapping("/yanWu2")
    public Msg queryYanwu2(@RequestParam("pageNum") int pageNum){
        Page<TestBean2> testBean2s = testService2.queryAll(pageNum);
        PageHelper.startPage(pageNum,10);
        Page<YanWu2> yanWu1s = new Page<>() ;
        yanWu1s.setTotal(testBean2s.getTotal());
        for (TestBean2 testBean2 : testBean2s) {
            String data = testBean2.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String yanWu1 = jsonObject.getString("YanWu2");
            yanWu1s.add(new YanWu2(yanWu1,"烟雾传感器2",testBean2.revtime));

        }
        String s = JSON.toJSONString(yanWu1s);

        return new Msg(s,testBean2s.getTotal());
    }
    @RequestMapping("/light")
    public Msg queryLight(@RequestParam("pageNum") int pageNum){
        Page<TestBean2> testBean2s = testService2.queryAll(pageNum);
        PageHelper.startPage(pageNum,10);
        Page<Light> lights = new Page<>()  ;
        for (TestBean2 testBean2 : testBean2s) {
            String data = testBean2.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String Light = jsonObject.getString("light");
            lights.add(new Light(Light,"光照传感器",testBean2.revtime));
        }
        String s = JSON.toJSONString(lights);
        System.out.println(JSON.toJSONString(new Msg(s, testBean2s.getTotal())));
        return new Msg(s,testBean2s.getTotal());
    }
    @RequestMapping("/temp")
    public Msg queryTemp(@RequestParam("pageNum") int pageNum){
        Page<TestBean2> testBean2s = testService2.queryAll(pageNum);
        PageHelper.startPage(pageNum,10);
        Page<Temp> temps = new Page<>() ;
        for (TestBean2 testBean2 : testBean2s) {
            String data = testBean2.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String temperature = jsonObject.getString("temperature");
            temps.add(new Temp(temperature,"温度传感器",testBean2.revtime));
        }
        String s = JSON.toJSONString(temps);

        return new Msg(s, testBean2s.getTotal());
    }
    @RequestMapping("/humidity")
    public Msg queryHum(@RequestParam("pageNum") int pageNum){
        Page<TestBean2> testBean2s = testService2.queryAll(pageNum);
        PageHelper.startPage(pageNum,10);
        Page<Humidity> hums = new Page<>()  ;


        for (TestBean2 testBean2 : testBean2s) {
            String data = testBean2.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String temperature = jsonObject.getString("humidity");
            hums.add(new Humidity(temperature,"湿度传感器",testBean2.revtime));
        }
        String s = JSON.toJSONString(hums);

        return new Msg(s,testBean2s.getTotal());
    }

    //删除
    @RequestMapping("/delete")
    public void deleteData(@RequestParam("id") int id){
        testService2.deleteTestBean(id);
    }
    //修改
    @RequestMapping("/update")
    public void updateData(TestBean2 testBean2){
        testService2.updateTestBean(testBean2);
    }
}
