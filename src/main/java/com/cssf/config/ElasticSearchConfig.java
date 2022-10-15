package com.cssf.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/10/13 - 10 -13 - 14:14
 * @Description: com.example.esapi.config
 * @Version: 1.0
 */
//找对象 放到Spring中待用
@Configuration
public class ElasticSearchConfig {
    //spring <bean id=  class>
    @Bean
    @Qualifier("restHighLevelClient")
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("127.0.0.1",9200,"http")
                )
        );
        return client;
    }
}
