package com.cssf.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 16:45
 * @Description: com.cssf.pojo.book
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String bname;
    /*
     * 1.未借
     * 2.已借
     */
    private String state;

    private String info;

    private String local;
}
