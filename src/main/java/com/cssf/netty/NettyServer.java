package com.cssf.netty;



import com.cssf.config.Config;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/10 - 05 -10 - 19:47
 * @Description: com.cssf.netty
 * @Version: 1.0
 */
@Component
@Slf4j
public class NettyServer implements Runnable{
    @Resource
    private ServerInitializer serverInitializer;
//    @PostConstruct
    public void start(InetSocketAddress address) throws InterruptedException{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(128);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class) //异步TCP连接
                    .localAddress(address)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                    .childHandler(serverInitializer);
            Channel channel = b.bind(address).sync().channel();
            log.info("Netty start on port: {}", Config.serverPort);
            channel.closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    @Override
    public void run() {
        InetSocketAddress address = new InetSocketAddress(Config.serverPort);
        try {
            this.start(address);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
