package com.cssf.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 16:30
 * @Description: com.cssf.pojo.book
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BUser {
    private long id;
    private String name;

    /*
    * 0.未认证
    * 1.已认证
     */
    private String state;

}
