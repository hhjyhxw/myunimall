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
import com.icloud.modules.unimall.entity.UnimallFreightemplateCarriage;
import com.icloud.modules.unimall.service.UnimallFreightemplateCarriageService;
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
 * 菜单主连接： modules/unimall/unimallfreightemplatecarriage.html
 */
@RestController
@RequestMapping("unimall/unimallfreightemplatecarriage")
public class UnimallFreightemplateCarriageController {
    @Autowired
    private UnimallFreightemplateCarriageService unimallFreightemplateCarriageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallfreightemplatecarriage:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallFreightemplateCarriageService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallfreightemplatecarriage:info")
    public R info(@PathVariable("id") Long id){
        UnimallFreightemplateCarriage unimallFreightemplateCarriage = (UnimallFreightemplateCarriage)unimallFreightemplateCarriageService.getById(id);

        return R.ok().put("unimallFreightemplateCarriage", unimallFreightemplateCarriage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallfreightemplatecarriage:save")
    public R save(@RequestBody UnimallFreightemplateCarriage unimallFreightemplateCarriage){
        unimallFreightemplateCarriageService.save(unimallFreightemplateCarriage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallfreightemplatecarriage:update")
    public R update(@RequestBody UnimallFreightemplateCarriage unimallFreightemplateCarriage){
        ValidatorUtils.validateEntity(unimallFreightemplateCarriage);
        unimallFreightemplateCarriageService.updateById(unimallFreightemplateCarriage);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallfreightemplatecarriage:delete")
    public R delete(@RequestBody Long[] ids){
        unimallFreightemplateCarriageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
