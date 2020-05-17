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
import com.icloud.modules.unimall.entity.UnimallConfig;
import com.icloud.modules.unimall.service.UnimallConfigService;
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
 * 菜单主连接： modules/unimall/unimallconfig.html
 */
@RestController
@RequestMapping("unimall/unimallconfig")
public class UnimallConfigController {
    @Autowired
    private UnimallConfigService unimallConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallConfigService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallconfig:info")
    public R info(@PathVariable("id") Long id){
        UnimallConfig unimallConfig = (UnimallConfig)unimallConfigService.getById(id);

        return R.ok().put("unimallConfig", unimallConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallconfig:save")
    public R save(@RequestBody UnimallConfig unimallConfig){
        unimallConfigService.save(unimallConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallconfig:update")
    public R update(@RequestBody UnimallConfig unimallConfig){
        ValidatorUtils.validateEntity(unimallConfig);
        unimallConfigService.updateById(unimallConfig);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallconfig:delete")
    public R delete(@RequestBody Long[] ids){
        unimallConfigService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
