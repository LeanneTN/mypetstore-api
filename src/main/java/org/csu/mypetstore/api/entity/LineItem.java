package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lineitem")
public class LineItem {
    @TableId(value = "orderid", type = IdType.INPUT)
    private int orderId;
    @TableId(value = "linenum", type = IdType.INPUT)
    private int lineNumber;
    @TableId(value = "itemid")
    private String itemId;
    private int quantity;
    @TableId(value = "unitprice")
    private BigDecimal unitPrice;
}
