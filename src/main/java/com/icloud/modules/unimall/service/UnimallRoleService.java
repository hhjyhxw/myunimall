package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 角色表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 */
@Service
@Transactional
public class UnimallRoleService extends BaseServiceImpl<UnimallRoleMapper,UnimallRole> {

    @Autowired
    private UnimallRoleMapper unimallRoleMapper;
}

