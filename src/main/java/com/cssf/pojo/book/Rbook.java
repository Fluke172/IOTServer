package com.cssf.pojo.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/17 - 09 -17 - 16:53
 * @Description: com.cssf.pojo.book
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rbook {
    private int id;
    private String bname;
    private long userid;
    private long btime;
    private long endtime;

    public Rbook(String bname, long userid, long btime, long endtime) {
        this.bname = bname;
        this.userid = userid;
        this.btime = btime;
        this.endtime = endtime;
    }
}
