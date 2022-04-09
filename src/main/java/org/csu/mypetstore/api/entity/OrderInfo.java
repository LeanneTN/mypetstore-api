package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.util.Date;

@Data
@TableName("orderinfo")
public class OrderInfo {
    @MppMultiId
    @TableField(value = "orderid")
    private int orderId;
    @MppMultiId
    @TableField(value = "linenum")
    private int lineNum;
}
