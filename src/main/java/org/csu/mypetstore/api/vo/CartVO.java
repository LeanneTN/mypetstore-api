package org.csu.mypetstore.api.vo;

import lombok.Data;
import org.csu.mypetstore.api.entity.CartItem;

import java.math.BigDecimal;
import java.util.*;

@Data
public class CartVO {
    private List<CartItem> itemList = new ArrayList<CartItem>();

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");

        int len = itemList.size();
        for (int i = 0;i < len;i ++){
            CartItem cartItem = itemList.get(i);
            BigDecimal listPrice = cartItem.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }
}
