package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 角色表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 */
public interface UnimallRoleMapper extends BaseMapper<UnimallRole> {

	List<UnimallRole> queryMixList(Map<String, Object> map);
}
