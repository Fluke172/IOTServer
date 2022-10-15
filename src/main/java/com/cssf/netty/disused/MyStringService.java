package com.cssf.netty.disused;

import io.netty.channel.Channel;
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
public class MyStringService extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        Channel channel = channelHandlerContext.channel();
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        System.out.println(msg + "--------------------这个其实时String吧"+channel.read());

    }
}
