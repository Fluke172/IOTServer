package com.cssf.pojo.disused;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/5/25 - 05 -25 - 9:20
 * @Description: com.cssf.pojo
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class TestBean implements Serializable {
    protected int id;
    protected float data;
    protected Timestamp time;



    public TestBean(float data, Timestamp time) {
        this.data = data;
        this.time = time;
    }
}
