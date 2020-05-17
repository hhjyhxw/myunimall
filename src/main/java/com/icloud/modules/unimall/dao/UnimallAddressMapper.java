package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:02
 */
public interface UnimallAddressMapper extends BaseMapper<UnimallAddress> {

	List<UnimallAddress> queryMixList(Map<String, Object> map);
}
