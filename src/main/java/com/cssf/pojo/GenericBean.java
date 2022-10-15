package com.cssf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/6 - 09 -06 - 23:30
 * @Description: com.cssf.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericBean {
    private int id;

    private String name;

    private String dataKey;

    private String dataValue;

    private Long revtime;



    public GenericBean(String name, String dataKey, String dataValue, Long revtime) {
        this.name = name;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
        this.revtime = revtime;
    }
}
