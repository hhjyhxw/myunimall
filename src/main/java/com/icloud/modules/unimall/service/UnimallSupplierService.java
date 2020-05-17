package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallSupplier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallSupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 商户表 商户表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Service
@Transactional
public class UnimallSupplierService extends BaseServiceImpl<UnimallSupplierMapper,UnimallSupplier> {

    @Autowired
    private UnimallSupplierMapper unimallSupplierMapper;
}

