package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallFreightemplateCarriage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 */
public interface UnimallFreightemplateCarriageMapper extends BaseMapper<UnimallFreightemplateCarriage> {

	List<UnimallFreightemplateCarriage> queryMixList(Map<String, Object> map);
}
