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
import com.icloud.modules.unimall.entity.UnimallFootprint;
import com.icloud.modules.unimall.service.UnimallFootprintService;
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
 * 菜单主连接： modules/unimall/unimallfootprint.html
 */
@RestController
@RequestMapping("unimall/unimallfootprint")
public class UnimallFootprintController {
    @Autowired
    private UnimallFootprintService unimallFootprintService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallfootprint:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallFootprintService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallfootprint:info")
    public R info(@PathVariable("id") Long id){
        UnimallFootprint unimallFootprint = (UnimallFootprint)unimallFootprintService.getById(id);

        return R.ok().put("unimallFootprint", unimallFootprint);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallfootprint:save")
    public R save(@RequestBody UnimallFootprint unimallFootprint){
        unimallFootprintService.save(unimallFootprint);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallfootprint:update")
    public R update(@RequestBody UnimallFootprint unimallFootprint){
        ValidatorUtils.validateEntity(unimallFootprint);
        unimallFootprintService.updateById(unimallFootprint);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallfootprint:delete")
    public R delete(@RequestBody Long[] ids){
        unimallFootprintService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
