package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallUserCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 用户优惠卷表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
public interface UnimallUserCouponMapper extends BaseMapper<UnimallUserCoupon> {

	List<UnimallUserCoupon> queryMixList(Map<String,Object> map);
}
