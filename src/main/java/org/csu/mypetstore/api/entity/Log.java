package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("log")
public class Log {
    private int index;
    private String info;
    private String username;
}
