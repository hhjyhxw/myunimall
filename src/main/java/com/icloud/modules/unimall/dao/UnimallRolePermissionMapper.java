package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 权限表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 */
public interface UnimallRolePermissionMapper extends BaseMapper<UnimallRolePermission> {

	List<UnimallRolePermission> queryMixList(Map<String, Object> map);
}
