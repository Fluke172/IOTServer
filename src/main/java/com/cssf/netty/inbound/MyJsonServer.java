package com.cssf.netty.inbound;

import com.alibaba.fastjson.JSONObject;

import com.cssf.mapper.BookMapper;
import com.cssf.pojo.GenericBean;
import com.cssf.pojo.TestBean2;
import com.cssf.pojo.book.BUser;
import com.cssf.pojo.book.Book;
import com.cssf.service.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/4 - 09 -04 - 11:00
 * @Description: com.cssf.netty
 * @Version: 1.0
 */
@ChannelHandler.Sharable
@Component
public class MyJsonServer extends SimpleChannelInboundHandler<ByteBuf> {
    @Autowired
    private TestService2 testService2;
    @Autowired
    private GenericService genericService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BatteryService batteryService;


    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();

    //sensor用来存实时数据
    public static  ConcurrentHashMap<String,String> lsjsensor =  new ConcurrentHashMap<>();
    public static  ConcurrentHashMap<String,String> hhhsensor =  new ConcurrentHashMap<>();


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
        System.out.println( "------------------" + ctx.channel().remoteAddress().toString().split(":")[0]);
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.err.println("有客户端与服务器断开连接。客户端地址：" + channel.remoteAddress());
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        String tdata = byteBuf.toString(CharsetUtil.UTF_8);
//        if (!queue.isEmpty()){
//            queue.pollFirst();
//        }else{
//            queue.addLast(tdata);
//        }


        System.out.println(tdata);

        JSONObject jsonObject1 = JSONObject.parseObject(tdata);
        String name = jsonObject1.getString("name");
        String data = jsonObject1.getString("data");

        //这里用集合直接存直接给 websocket
        if (name.equals("lsjjj")){
            System.out.println("接收到了 ------------------------lsjJj");
            lsjsensor.putIfAbsent("name",name);
            lsjsensor.put("data",data);
            lsjsensor.put("revtime",String.valueOf(new Date().getTime()));
        }else if (name.equals("HAHAHA")){
            hhhsensor.put("name",name);
            hhhsensor.put("data",data);
            hhhsensor.put("revtime",String.valueOf(new Date().getTime()));
        }


        HashMap<String,String> datamap = JSONObject.parseObject(data, HashMap.class);


        if (datamap.get("id") != null){
            //新书传来（就是新标签被识别 ）
            Long id = Long.parseLong( datamap.get("id"));
            bookService.insertBook(new Book(id,"未设置","1","未设置","未设置"));
        }else if(datamap.get("uid") != null && datamap.get("uname")!= null) {
            //新的RFID被识别 这里的uname是否可以不用？
            long uid = Long.parseLong(datamap.get("uid"));
            String uname = datamap.get("uname");
            userService.insertUser(new BUser(uid,uname,"0"));

            if (userService.queryUserById(uid).getState().equals("1")){
                //此处需要返回一个让LED常亮10s的命令
                channelHandlerContext.channel().writeAndFlush("yse");
            }
        }else {
                //剩下的都是传感器
                Map map = JSONObject.parseObject(data, Map.class);
                Date revtime = new Date();
                long time = revtime.getTime();
//        testService2.insertData(new TestBean2(name,data,time));
                for (Object o : map.keySet()) {
                    genericService.insertData(new GenericBean(name, o.toString(), map.get(o).toString(), time));
                }
                testService2.insertData(new TestBean2(name, data, time));
                Channel channel = channelHandlerContext.channel();

            }
        }

    }

