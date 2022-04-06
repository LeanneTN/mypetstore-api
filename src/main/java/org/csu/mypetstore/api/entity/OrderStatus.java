package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class OrderStatus {
    @TableId(value = "orderid", type = IdType.INPUT)
    private int orderId;
    @TableId(value = "linenum", type = IdType.INPUT)
    private int lineNum;
    private Date timestamp;
    private String status;
}
