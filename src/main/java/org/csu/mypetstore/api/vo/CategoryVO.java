package org.csu.mypetstore.api.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private List<ProductVO> productVOList;

    private String CategoryId;
    private String name;
    private String description;
}
