package com.icloud.modules.unimall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.basecommon.service.BaseServiceImpl;
import com.icloud.common.MapEntryUtils;
import com.icloud.common.PageUtils;
import com.icloud.modules.unimall.dao.UnimallSpuMapper;
import com.icloud.modules.unimall.entity.UnimallSpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Service
@Transactional
public class UnimallSpuService extends BaseServiceImpl<UnimallSpuMapper,UnimallSpu> {

    @Autowired
    private UnimallSpuMapper unimallSpuMapper;

    @Override
    public PageUtils<UnimallSpu> findByPage(int pageNo, int pageSize, Map<String, Object> query) {
        Map<String, Object> oldquery = query;
        try {
            Class<UnimallSpu> clazz = (Class<UnimallSpu>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            query =  MapEntryUtils.mapvalueToBeanValueAndBeanProperyToColum(query, clazz);
            for(String key:query.keySet()){
                System.out.println("key="+key+" and value=" +query.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        QueryWrapper qw = new QueryWrapper();
        if(query.entrySet().size()>0) {
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                if(entry.getValue()!=null && !"".equals(entry.getValue().toString())) {
                    if ("title".equals(entry.getKey())){
                        qw.like(entry.getKey(), entry.getValue());
                    }else{
                        qw.eq(entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        //排序：
        qw.orderByDesc("gmt_create");
        if(oldquery.entrySet().size()>0) {
            for (Map.Entry<String, Object> entry : oldquery.entrySet()) {
                if(entry.getValue()!=null && !"".equals(entry.getValue().toString())) {
                    if("sales".equals(entry.getKey()) && entry.getValue().toString().equals("1")) {//销量升序
                        qw.orderByAsc("sales");
                    }
                    if("sales".equals(entry.getKey()) && entry.getValue().toString().equals("0")) {//销量降序
                        qw.orderByAsc("sales");
                    }
                }
            }
        }

        PageHelper.startPage(pageNo, pageSize);
        List<UnimallSpu> list = super.list(qw);
        PageInfo<UnimallSpu> pageInfo = new PageInfo<UnimallSpu>(list);
        PageUtils<UnimallSpu> page = new PageUtils<UnimallSpu>(list,(int)pageInfo.getTotal(),pageSize,pageNo);
        return page;
    }
}

