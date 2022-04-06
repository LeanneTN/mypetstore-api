package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lineitem")
public class LineItem {
    @TableId(value = "orderid")
    private int orderId;
    @TableId(value = "linenum")
    private int lineNumber;
    @TableId(value = "itemid")
    private String itemId;
    private int quantity;
    @TableId(value = "unitprice")
    private BigDecimal unitPrice;
}
