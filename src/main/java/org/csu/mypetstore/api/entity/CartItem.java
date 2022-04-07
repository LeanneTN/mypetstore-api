package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("cartitem")
public class CartItem {
    @TableField(value = "itemid")
    private String itemId;
    @TableField(value = "productid")
    private String productId;
    private String descn;
    @TableField(value = "instock")
    private boolean inStock;
    private int quantity;
    @TableField(value = "listprice")
    private BigDecimal listPrice;
    @TableField(value = "totalprice")
    private BigDecimal totalPrice;
    @TableField(value = "buyername")
    private String buyerName;
}
