package com.icloud.modules.unimall.controller;

import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;
import com.icloud.modules.unimall.entity.UnimallRolePermission;
import com.icloud.modules.unimall.service.UnimallRolePermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 权限表
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 * 菜单主连接： modules/unimall/unimallrolepermission.html
 */
@RestController
@RequestMapping("unimall/unimallrolepermission")
public class UnimallRolePermissionController {
    @Autowired
    private UnimallRolePermissionService unimallRolePermissionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallrolepermission:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallRolePermissionService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallrolepermission:info")
    public R info(@PathVariable("id") Integer id){
        UnimallRolePermission unimallRolePermission = (UnimallRolePermission)unimallRolePermissionService.getById(id);

        return R.ok().put("unimallRolePermission", unimallRolePermission);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallrolepermission:save")
    public R save(@RequestBody UnimallRolePermission unimallRolePermission){
        unimallRolePermissionService.save(unimallRolePermission);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallrolepermission:update")
    public R update(@RequestBody UnimallRolePermission unimallRolePermission){
        ValidatorUtils.validateEntity(unimallRolePermission);
        unimallRolePermissionService.updateById(unimallRolePermission);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallrolepermission:delete")
    public R delete(@RequestBody Integer[] ids){
        unimallRolePermissionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
