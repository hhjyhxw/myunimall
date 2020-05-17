package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallFreightemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 */
public interface UnimallFreightemplateMapper extends BaseMapper<UnimallFreightemplate> {

	List<UnimallFreightemplate> queryMixList(Map<String, Object> map);
}
