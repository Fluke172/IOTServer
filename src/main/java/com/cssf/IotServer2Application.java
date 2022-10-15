package com.cssf;


import com.cssf.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@EnableCaching
@EnableTransactionManagement

@SpringBootApplication
@MapperScan("com.cssf.mapper")
public class IotServer2Application extends WebMvcConfigurationSupport implements CommandLineRunner {

    @Autowired
    private NettyServer nettyServer;
    public static void main(String[] args)  throws Exception{
        ApplicationContext context = new SpringApplicationBuilder(IotServer2Application.class).run(args);

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void run(String... args) throws Exception {


        new Thread(nettyServer).start();
    }
}
