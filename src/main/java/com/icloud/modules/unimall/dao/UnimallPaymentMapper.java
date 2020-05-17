package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallPayment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 支付流水表 支付流水表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
public interface UnimallPaymentMapper extends BaseMapper<UnimallPayment> {

	List<UnimallPayment> queryMixList(Map<String,Object> map);
}
