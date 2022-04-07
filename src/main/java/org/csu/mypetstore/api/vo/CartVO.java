package org.csu.mypetstore.api.vo;

import java.util.*;

public class CartVO {
    private Map<String, CartItemVO> itemMap = Collections.synchronizedMap(new HashMap<String, CartItemVO>());
    private List<CartItemVO> itemList = new ArrayList<CartItemVO>();
}
