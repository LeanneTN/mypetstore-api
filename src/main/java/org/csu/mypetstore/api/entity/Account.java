package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account")
public class Account {
    @TableId(value = "userid", type = IdType.INPUT)
    private String username;
    private String password;
    private String email;
    @TableField(value = "firstname")
    private String firstName;
    @TableField(value = "lastname")
    private String lastName;
    private String status;
    @TableField(value = "addr1")
    private String address1;
    @TableField(value = "addr2")
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
}
