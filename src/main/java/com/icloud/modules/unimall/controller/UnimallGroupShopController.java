package com.icloud.modules.unimall.controller;

import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;
import com.icloud.modules.unimall.entity.UnimallGroupShop;
import com.icloud.modules.unimall.service.UnimallGroupShopService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 * 菜单主连接： modules/unimall/unimallgroupshop.html
 */
@RestController
@RequestMapping("unimall/unimallgroupshop")
public class UnimallGroupShopController {
    @Autowired
    private UnimallGroupShopService unimallGroupShopService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallgroupshop:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallGroupShopService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallgroupshop:info")
    public R info(@PathVariable("id") Long id){
        UnimallGroupShop unimallGroupShop = (UnimallGroupShop)unimallGroupShopService.getById(id);

        return R.ok().put("unimallGroupShop", unimallGroupShop);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallgroupshop:save")
    public R save(@RequestBody UnimallGroupShop unimallGroupShop){
        unimallGroupShopService.save(unimallGroupShop);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallgroupshop:update")
    public R update(@RequestBody UnimallGroupShop unimallGroupShop){
        ValidatorUtils.validateEntity(unimallGroupShop);
        unimallGroupShopService.updateById(unimallGroupShop);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallgroupshop:delete")
    public R delete(@RequestBody Long[] ids){
        unimallGroupShopService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
