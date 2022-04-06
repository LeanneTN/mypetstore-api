package org.csu.mypetstore.api.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.api.entity.ItemInventory;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInventoryMapper extends BaseMapper<ItemInventory> {
}
