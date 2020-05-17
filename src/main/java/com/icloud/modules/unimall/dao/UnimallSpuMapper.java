package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
public interface UnimallSpuMapper extends BaseMapper<UnimallSpu> {

	List<UnimallSpu> queryMixList(Map<String,Object> map);
}
