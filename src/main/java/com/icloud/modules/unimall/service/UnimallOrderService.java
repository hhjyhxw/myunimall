package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 */
@Service
@Transactional
public class UnimallOrderService extends BaseServiceImpl<UnimallOrderMapper,UnimallOrder> {

    @Autowired
    private UnimallOrderMapper unimallOrderMapper;
}

