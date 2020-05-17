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
import com.icloud.modules.unimall.entity.UnimallGroupShopSku;
import com.icloud.modules.unimall.service.UnimallGroupShopSkuService;
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
 * 菜单主连接： modules/unimall/unimallgroupshopsku.html
 */
@RestController
@RequestMapping("unimall/unimallgroupshopsku")
public class UnimallGroupShopSkuController {
    @Autowired
    private UnimallGroupShopSkuService unimallGroupShopSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallgroupshopsku:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallGroupShopSkuService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallgroupshopsku:info")
    public R info(@PathVariable("id") Long id){
        UnimallGroupShopSku unimallGroupShopSku = (UnimallGroupShopSku)unimallGroupShopSkuService.getById(id);

        return R.ok().put("unimallGroupShopSku", unimallGroupShopSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallgroupshopsku:save")
    public R save(@RequestBody UnimallGroupShopSku unimallGroupShopSku){
        unimallGroupShopSkuService.save(unimallGroupShopSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallgroupshopsku:update")
    public R update(@RequestBody UnimallGroupShopSku unimallGroupShopSku){
        ValidatorUtils.validateEntity(unimallGroupShopSku);
        unimallGroupShopSkuService.updateById(unimallGroupShopSku);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallgroupshopsku:delete")
    public R delete(@RequestBody Long[] ids){
        unimallGroupShopSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
