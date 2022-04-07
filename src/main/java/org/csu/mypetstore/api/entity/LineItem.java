package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("lineitem")
public class LineItem {
    @MppMultiId
    @TableField(value = "orderid")
    private int orderId;
    @MppMultiId
    @TableField(value = "linenum")
    private int lineNumber;
    @TableField(value = "itemid")
    private String itemId;
    private int quantity;
    @TableField(value = "unitprice")
    private BigDecimal unitPrice;

}
