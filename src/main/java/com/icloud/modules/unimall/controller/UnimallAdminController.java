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
import com.icloud.modules.unimall.entity.UnimallAdmin;
import com.icloud.modules.unimall.service.UnimallAdminService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:02
 * 菜单主连接： modules/unimall/unimalladmin.html
 */
@RestController
@RequestMapping("unimall/unimalladmin")
public class UnimallAdminController {
    @Autowired
    private UnimallAdminService unimallAdminService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimalladmin:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallAdminService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimalladmin:info")
    public R info(@PathVariable("id") Long id){
        UnimallAdmin unimallAdmin = (UnimallAdmin)unimallAdminService.getById(id);

        return R.ok().put("unimallAdmin", unimallAdmin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimalladmin:save")
    public R save(@RequestBody UnimallAdmin unimallAdmin){
        unimallAdminService.save(unimallAdmin);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimalladmin:update")
    public R update(@RequestBody UnimallAdmin unimallAdmin){
        ValidatorUtils.validateEntity(unimallAdmin);
        unimallAdminService.updateById(unimallAdmin);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimalladmin:delete")
    public R delete(@RequestBody Long[] ids){
        unimallAdminService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
