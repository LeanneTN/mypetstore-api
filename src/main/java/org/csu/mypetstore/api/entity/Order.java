package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("orders")
public class Order {
    @TableId(value = "orderid", type = IdType.INPUT)
    private int orderId;
    private String username;
    @TableField(value = "orderdate")
    private Date orderDate;
    @TableField(value = "shipaddress1")
    private String shipAddress1;
    @TableField(value = "shipaddress2")
    private String shipAddress2;
    @TableField(value = "shipcity")
    private String shipCity;
    @TableField(value = "shipstate")
    private String shipState;
    @TableField(value = "shipzip")
    private String shipZip;
    @TableField(value = "shipcountry")
    private String shipCountry;
    @TableField(value = "billaddress1")
    private String billAddress1;
    @TableField(value = "billaddress2")
    private String billAddress2;
    @TableField(value = "billcity")
    private String billCity;
    @TableField(value = "billstate")
    private String billState;
    @TableField(value = "billzip")
    private String billZip;
    @TableField(value = "billcountry")
    private String billCountry;
    private String courier;
    @TableField(value = "totalprice")
    private BigDecimal totalPrice;
    @TableField(value = "billtofirstname")
    private String billToFirstName;
    @TableField(value = "billtolastname")
    private String billToLastName;
    @TableField(value = "shiptofirstname")
    private String shipToFirstName;
    @TableField(value = "shiptolastname")
    private String shipToLastName;
    @TableField(value = "creditcard")
    private String creditCard;
    @TableField(value = "expirydate")
    private String expiryDate;
    @TableField(value = "cardtype")
    private String cardType;
    private String locale;
    private String status;
    private Date timestamp;
}
