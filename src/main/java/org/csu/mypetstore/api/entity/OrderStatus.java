package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import java.util.Date;

public class OrderStatus {
    @MppMultiId
    @TableField(value = "orderid")
    private int orderId;
    @MppMultiId
    @TableField(value = "linenum")
    private int lineNum;
    private Date timestamp;
    private String status;
}
