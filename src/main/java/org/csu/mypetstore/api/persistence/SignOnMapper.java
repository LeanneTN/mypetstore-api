package org.csu.mypetstore.api.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.api.entity.SignOn;
import org.springframework.stereotype.Repository;

@Repository
public interface SignOnMapper extends BaseMapper<SignOn> {
}
