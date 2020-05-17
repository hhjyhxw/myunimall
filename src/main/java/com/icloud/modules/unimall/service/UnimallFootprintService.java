package com.icloud.modules.unimall.service;

import com.icloud.modules.unimall.entity.UnimallFootprint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.modules.unimall.dao.UnimallFootprintMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 */
@Service
@Transactional
public class UnimallFootprintService extends BaseServiceImpl<UnimallFootprintMapper,UnimallFootprint> {

    @Autowired
    private UnimallFootprintMapper unimallFootprintMapper;
}

