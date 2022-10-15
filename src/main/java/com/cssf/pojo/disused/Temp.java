package com.cssf.pojo.disused;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/6 - 09 -06 - 19:45
 * @Description: com.cssf.pojo
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Temp implements Serializable {
    private String data;

    private String name;

    private Long revtime;
}
