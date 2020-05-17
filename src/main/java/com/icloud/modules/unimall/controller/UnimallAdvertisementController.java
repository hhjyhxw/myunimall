package com.icloud.modules.unimall.controller;

import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;
import com.icloud.modules.unimall.entity.UnimallAdvertisement;
import com.icloud.modules.unimall.service.UnimallAdvertisementService;
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
 * 菜单主连接： modules/unimall/unimalladvertisement.html
 */
@RestController
@RequestMapping("unimall/unimalladvertisement")
public class UnimallAdvertisementController {
    @Autowired
    private UnimallAdvertisementService unimallAdvertisementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimalladvertisement:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallAdvertisementService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimalladvertisement:info")
    public R info(@PathVariable("id") Long id){
        UnimallAdvertisement unimallAdvertisement = (UnimallAdvertisement)unimallAdvertisementService.getById(id);

        return R.ok().put("unimallAdvertisement", unimallAdvertisement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimalladvertisement:save")
    public R save(@RequestBody UnimallAdvertisement unimallAdvertisement){
        unimallAdvertisementService.save(unimallAdvertisement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimalladvertisement:update")
    public R update(@RequestBody UnimallAdvertisement unimallAdvertisement){
        ValidatorUtils.validateEntity(unimallAdvertisement);
        unimallAdvertisementService.updateById(unimallAdvertisement);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimalladvertisement:delete")
    public R delete(@RequestBody Long[] ids){
        unimallAdvertisementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
