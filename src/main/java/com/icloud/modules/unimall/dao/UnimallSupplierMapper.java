package com.icloud.modules.unimall.dao;

import com.icloud.modules.unimall.entity.UnimallSupplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * 商户表 商户表
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
public interface UnimallSupplierMapper extends BaseMapper<UnimallSupplier> {

	List<UnimallSupplier> queryMixList(Map<String,Object> map);
}
