package com.cssf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/5 - 09 -05 - 20:43
 * @Description: com.cssf.pojo
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryTime {

    private Long beginTime;
    private Long endTime;
}
