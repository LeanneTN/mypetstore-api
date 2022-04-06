package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("orders")
public class Orders {
    @TableId(value = "orderid", type = IdType.INPUT)
    private int orderId;
    private String username;
    @TableId(value = "orderdate")
    private Date orderDate;
    @TableId(value = "shipaddress1")
    private String shipAddress1;
    @TableId(value = "shipaddress2")
    private String shipAddress2;
    @TableId(value = "shipcity")
    private String shipCity;
    @TableId(value = "shipstate")
    private String shipState;
    @TableId(value = "shpizip")
    private String shipZip;
    @TableId(value = "shipcountry")
    private String shipCountry;
    @TableId(value = "billaddress1")
    private String billAddress1;
    @TableId(value = "billaddress2")
    private String billAddress2;
    @TableId(value = "billcity")
    private String billCity;
    @TableId(value = "billstate")
    private String billState;
    @TableId(value = "billzip")
    private String billZip;
    @TableId(value = "billcountry")
    private String billCountry;
    private String courier;
    @TableId(value = "totalprice")
    private BigDecimal totalPrice;
    @TableId(value = "billtofirstname")
    private String billToFirstName;
    @TableId(value = "billtolastname")
    private String billToLastName;
    @TableId(value = "shiptofirstname")
    private String shipToFirstName;
    @TableId(value = "billtolastname")
    private String shipToLastName;
    @TableId(value = "creditcard")
    private String creditCard;
    @TableId(value = "expirydate")
    private String expiryDate;
    @TableId(value = "cardtype")
    private String cardType;
    private String locale;
}
