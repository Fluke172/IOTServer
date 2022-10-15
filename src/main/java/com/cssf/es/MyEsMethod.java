package com.cssf.es;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/10/15 - 10 -15 - 19:01
 * @Description: com.cssf.es
 * @Version: 1.0
 */
//我的Es方法
@Component
public class MyEsMethod {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //将得到的数据插入Es
    public Boolean insertEs(String jsonData) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");

        bulkRequest.add(new IndexRequest("iot")
                .source(jsonData, XContentType.JSON));
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();
    }
    //好像不适用于这个场景 此场景都是精确查询0.0
    //^ ^ 我还是拿RabbitMQ写一个短信服务器吧~~~

}
