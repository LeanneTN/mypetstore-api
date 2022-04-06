package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("supplier")
public class Supplier {
    @TableId(value = "suppid", type = IdType.INPUT)
    private int supplierId;
    private String name;
    private String status;
    @TableField(value = "addr1")
    private String address1;
    @TableField(value = "addr2")
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
}
