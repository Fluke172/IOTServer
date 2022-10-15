package com.cssf.websocket;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cssf.emil.sendMail;
import com.cssf.mapper.TestMapper2;
import com.cssf.netty.inbound.MyJsonServer;
import com.cssf.service.GenericService;
import com.cssf.service.TestService2;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/2 - 09 -02 - 22:33
 * @Description: com.cssf.controller
 * @Version: 1.0
 */
@ServerEndpoint(value = "/websocket/{userId}",encoders = {ServerEncoder.class})
@Component
public class WebSocket {
    private static TestMapper2 testMapper2;

    private static TestService2 testService;

    private final static Logger logger = LogManager.getLogger(WebSocket.class);

    private static int onlineCount = 0;

    private static GenericService genericService;


    private static sendMail sendMail;
    /*
    set注入 这样能解决空指针问题？
     */
    @Autowired
    public void setApplicationConText(TestService2 testService,GenericService genericService,TestMapper2 testMapper2,sendMail sendMail){
        WebSocket.testService = testService;
        WebSocket.genericService = genericService;
        WebSocket.testMapper2 = testMapper2;
        WebSocket.sendMail = sendMail;

    }

    /**
     * concurrent包的线程安全Map，用来存放每个客户端对应的MyWebSocket对象
     */
    private static ConcurrentHashMap<String, WebSocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */

    private Session session;
    private String userId;


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        System.out.println(session+ "------------THIS IS SESSION OF" +userId);
        this.userId = userId;
        //加入map
        webSocketMap.put(userId, this);
        addOnlineCount();           //在线数加1
        logger.info("用户{}连接成功,当前在线人数为{}", userId, getOnlineCount());
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                try {
                    //这样效率是否会高一点呢
//                    if (MyJsonServer.queue.pollLast() != null){
//                        session.getBasicRemote().sendText(JSONUtil.toJsonStr(MyJsonServer.queue.pollLast()));
//                    }

                    sendMessage();
                } catch (IOException e) {
                    logger.error("IO异常");
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();        // 添加执行任务(延迟 1s 执行，每 3s 执行一次)
        timer.schedule(timerTask, 1000, 3000);

    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从map中删除
        webSocketMap.remove(userId);
        subOnlineCount();           //在线数减1
        logger.info("用户{}关闭连接！当前在线人数为{}", userId, getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws MessagingException {

        logger.info("来自客户端用户：{} 消息:{}",userId, message);
        if (message.equals("f")){
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        sendMail.sendMailUtils("警告","烟雾浓度过高");
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
            );
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();

        }
        HashMap<String, Channel> map = new HashMap<>();
        MyJsonServer.users.forEach(channel -> channel.writeAndFlush(message));
//        for (Channel user : MyJsonServer.users) {
//            if (user.remoteAddress().toString().split(":")[0].equals("/192.168.1.109")){
//                map.put("lsjjj",user);
//            }else if(user.remoteAddress().toString().split(":")[0].equals("/192.168.197.231")){
//                map.put("HAHAHA",user);
//            }
//        }

//        JSONObject jsonObject = JSON.parseObject(message);
//        String name = jsonObject.getString("name");
//        String order = jsonObject.getString("order");
//        map.get(name).writeAndFlush(order);
        //群发消息
        /*for (String item : webSocketMap.keySet()) {
            try {
                webSocketMap.get(item).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * 发生错误时调用
     *
     * @OnError
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 向客户端发送消息
     */
    public void sendMessage() throws IOException, EncodeException {
        ArrayList<Object> objects = new ArrayList<>();

        //这个是分channel发送
//         for (Channel user : MyJsonServer.users) {
//
//             //System.out.println(user.remoteAddress().toString().split(":")[0].substring(1).equals("192.168.43.81") );
//             if (user.remoteAddress().toString().split(":")[0].equals("/192.168.1.109") ){
//                objects.add(testMapper2.queryRtimeData2());
//            }else if (user.remoteAddress().toString().split(":")[0].equals("/192.168.197.231")){
//                objects.add(testMapper2.queryRtimeData1());
//            }
//        }
//
//            this.session.getBasicRemote().sendText(JSONUtil.toJsonStr(objects));
            //this.session.getAsyncRemote().sendText(message);
//        122400

        //以下是内部容器实时传输
        String lsj = JSONArray.toJSON(MyJsonServer.lsjsensor).toString();
        String hhh = JSONArray.toJSON(MyJsonServer.hhhsensor).toString();
        Date date = new Date();
        String reward = "["+ lsj + "," + hhh + "]";
        System.out.println(reward);
        this.session.getBasicRemote().sendText(reward);
    }

    /**
     * 通过userId向客户端发送消息
     */
    public void sendMessageByUserId(String userId, String message) throws IOException, EncodeException {
        logger.info("服务端发送消息到{},消息：{}",userId,message);
        if(StrUtil.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage();
        }else{
            logger.error("用户{}不在线",userId);
        }

    }

    /**
     * 群发自定义消息
     */
    public static void sendAll() throws IOException {
        for (String item : webSocketMap.keySet()) {
            try {
                webSocketMap.get(item).sendMessage();
            } catch (IOException e) {
                continue;
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }




}
