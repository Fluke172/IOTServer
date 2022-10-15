package com.cssf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/10/15 - 10 -15 - 19:13
 * @Description: com.cssf.pojo
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Battery {
    private long id;
    private String state;
}
