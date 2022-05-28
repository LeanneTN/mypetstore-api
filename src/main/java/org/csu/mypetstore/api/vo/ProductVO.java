package org.csu.mypetstore.api.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductVO {
    //Product包含的Item
    private List<ItemVO> itemVOList;

    //Product的基本属性
    private String productId;
    private String categoryId;
    private String name;
    private String description;
    private String image;
}
