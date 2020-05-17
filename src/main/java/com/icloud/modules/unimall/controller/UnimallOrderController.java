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
import com.icloud.modules.unimall.entity.UnimallOrder;
import com.icloud.modules.unimall.service.UnimallOrderService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 * 菜单主连接： modules/unimall/unimallorder.html
 */
@RestController
@RequestMapping("unimall/unimallorder")
public class UnimallOrderController {
    @Autowired
    private UnimallOrderService unimallOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallorder:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallOrderService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallorder:info")
    public R info(@PathVariable("id") Long id){
        UnimallOrder unimallOrder = (UnimallOrder)unimallOrderService.getById(id);

        return R.ok().put("unimallOrder", unimallOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallorder:save")
    public R save(@RequestBody UnimallOrder unimallOrder){
        unimallOrderService.save(unimallOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallorder:update")
    public R update(@RequestBody UnimallOrder unimallOrder){
        ValidatorUtils.validateEntity(unimallOrder);
        unimallOrderService.updateById(unimallOrder);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallorder:delete")
    public R delete(@RequestBody Long[] ids){
        unimallOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
