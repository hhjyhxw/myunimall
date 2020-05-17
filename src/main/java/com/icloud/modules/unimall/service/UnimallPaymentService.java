package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallPayment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallPaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 支付流水表 支付流水表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Service
@Transactional
public class UnimallPaymentService extends BaseServiceImpl<UnimallPaymentMapper,UnimallPayment> {

    @Autowired
    private UnimallPaymentMapper unimallPaymentMapper;
}

