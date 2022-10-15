package com.cssf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/16 - 09 -16 - 11:29
 * @Description: com.cssf.iotserver.pojo
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCondition {
    private String equp;

    private Long beginTime;
    private Long endTime;

    private String sensor;


    public QueryCondition(String equp) {
        this.equp = equp;
    }
}
