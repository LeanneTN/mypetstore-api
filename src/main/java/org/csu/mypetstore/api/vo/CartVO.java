package org.csu.mypetstore.api.vo;

import lombok.Data;
import org.csu.mypetstore.api.entity.CartItem;

import java.util.*;

@Data
public class CartVO {
    private List<CartItem> itemList = new ArrayList<CartItem>();
}
