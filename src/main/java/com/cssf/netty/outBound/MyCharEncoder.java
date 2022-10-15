package com.cssf.netty.outBound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.stereotype.Component;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/6 - 09 -06 - 23:57
 * @Description: com.cssf.netty
 * @Version: 1.0
 */
@ChannelHandler.Sharable
@Component
public class MyCharEncoder extends MessageToByteEncoder<Character> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Character character, ByteBuf byteBuf) throws Exception {
        byteBuf.writeChar(character);
        System.out.println(byteBuf);
    }
}
