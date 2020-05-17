package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallRolePermission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 权限表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 */
@Service
@Transactional
public class UnimallRolePermissionService extends BaseServiceImpl<UnimallRolePermissionMapper,UnimallRolePermission> {

    @Autowired
    private UnimallRolePermissionMapper unimallRolePermissionMapper;
}

