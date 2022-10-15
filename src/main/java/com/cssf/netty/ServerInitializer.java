package com.cssf.netty;


import com.cssf.netty.disused.MyService;
import com.cssf.netty.disused.MyStringService;
import com.cssf.netty.inbound.MyDecoder;
import com.cssf.netty.inbound.MyJsonServer;
import com.cssf.netty.outBound.MyCharEncoder;
import com.cssf.netty.outBound.MyEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/10 - 05 -10 - 19:55
 * @Description: com.cssf.netty
 * @Version: 1.0
 */

@Component
public class ServerInitializer extends ChannelInitializer {
    @Resource
    private MyDecoder myDecoder;
    @Resource
    private MyEncoder myEncoder;

    @Resource
    private MyService myService;
    @Resource
    private MyStringService myStringService;
    @Resource
    private MyJsonServer jsonServer;
    @Resource
    private MyCharEncoder myCharEncoder;
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast("log",new LoggingHandler(LogLevel.INFO));
//        channel.pipeline().addLast(new MyHandler());
        channel.pipeline().addLast("encoder",myEncoder);
        channel.pipeline().addLast("mycharencoder",myCharEncoder);
        channel.pipeline().addLast("myencoder",new StringEncoder());
        channel.pipeline().addLast("decoder",new JsonObjectDecoder());
        channel.pipeline().addLast("json",jsonServer);
        channel.pipeline().addLast("stringservice",myStringService);
       channel.pipeline().addLast("myservice",myService);


    }

}
