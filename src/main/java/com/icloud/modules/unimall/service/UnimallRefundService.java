package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallRefund;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallRefundMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Service
@Transactional
public class UnimallRefundService extends BaseServiceImpl<UnimallRefundMapper,UnimallRefund> {

    @Autowired
    private UnimallRefundMapper unimallRefundMapper;
}

