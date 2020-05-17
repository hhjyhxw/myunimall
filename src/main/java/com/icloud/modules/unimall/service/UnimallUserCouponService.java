package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallUserCoupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallUserCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 用户优惠卷表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Service
@Transactional
public class UnimallUserCouponService extends BaseServiceImpl<UnimallUserCouponMapper,UnimallUserCoupon> {

    @Autowired
    private UnimallUserCouponMapper unimallUserCouponMapper;
}

