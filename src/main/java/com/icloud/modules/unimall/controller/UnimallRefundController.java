package com.icloud.modules.unimall.controller;

import java.util.Arrays;
import java.util.Map;
import com.icloud.basecommon.model.Query;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.icloud.modules.unimall.entity.UnimallRefund;
import com.icloud.modules.unimall.service.UnimallRefundService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 *  
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 * 菜单主连接： modules/unimall/unimallrefund.html
 */
@RestController
@RequestMapping("unimall/unimallrefund")
public class UnimallRefundController {
    @Autowired
    private UnimallRefundService unimallRefundService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallrefund:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallRefundService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallrefund:info")
    public R info(@PathVariable("id") String id){
        UnimallRefund unimallRefund = (UnimallRefund)unimallRefundService.getById(id);

        return R.ok().put("unimallRefund", unimallRefund);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallrefund:save")
    public R save(@RequestBody UnimallRefund unimallRefund){
        unimallRefundService.save(unimallRefund);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallrefund:update")
    public R update(@RequestBody UnimallRefund unimallRefund){
        ValidatorUtils.validateEntity(unimallRefund);
        unimallRefundService.updateById(unimallRefund);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallrefund:delete")
    public R delete(@RequestBody String[] ids){
        unimallRefundService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
