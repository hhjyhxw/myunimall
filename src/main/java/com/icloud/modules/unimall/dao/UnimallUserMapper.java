package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
public interface UnimallUserMapper extends BaseMapper<UnimallUser> {

	List<UnimallUser> queryMixList(Map<String,Object> map);
}
