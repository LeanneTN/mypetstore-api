package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("signon")
public class SignOn {
    private String username;
    private String password;
}
