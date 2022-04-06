package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("bannerdata")
public class BannerData {
    @TableId(value = "favcategory", type = IdType.INPUT)
    private String favouriteCategoryId;
    @TableField(value = "bannername")
    private String bannerName;
}
