package com.cssf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/2 - 09 -02 - 16:10
 * @Description: com.cssf.config
 * @Version: 1.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndopintExporter(){
        return new ServerEndpointExporter();
    }
}
