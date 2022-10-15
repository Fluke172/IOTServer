package com.cssf.netty.disused;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/10 - 05 -10 - 20:53
 * @Description: com.cssf.netty
 * @Version: 1.0
 */
@ChannelHandler.Sharable
@Component
public class MyService extends SimpleChannelInboundHandler<Float> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Float aFloat) throws Exception {
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        System.out.println(aFloat+"好好的");

    }
}
