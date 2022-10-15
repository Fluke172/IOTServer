package com.cssf.pojo.disused;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/18 - 09 -18 - 14:16
 * @Description: com.cssf.pojo.disused
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    private String data;
    private long total;
}
