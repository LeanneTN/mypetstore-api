package org.csu.mypetstore.api.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVO {
    //item中的字段
    private String itemId;
    private String productId;
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplierId;
    private String status;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    
    //product中的字段
    private String categoryId;
    private String name;
    private String description;
    private String image;

    //inventory中的字段
    private int quantity;
}
