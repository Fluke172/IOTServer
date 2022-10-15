package com.cssf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/4 - 09 -04 - 15:46
 * @Description: com.cssf.pojo
 * @Version: 1.0
 */
/*
    对应test2数据库
 */

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class TestBean2 implements Serializable {
    public int id;

    public String name;

    public String data;

    public Long revtime;

    public TestBean2(String name, String data, Long revtime) {
        this.name = name;
        this.data = data;
        this.revtime = revtime;
    }
}
