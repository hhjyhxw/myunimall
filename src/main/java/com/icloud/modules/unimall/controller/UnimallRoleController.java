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
import com.icloud.modules.unimall.entity.UnimallRole;
import com.icloud.modules.unimall.service.UnimallRoleService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 角色表
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 * 菜单主连接： modules/unimall/unimallrole.html
 */
@RestController
@RequestMapping("unimall/unimallrole")
public class UnimallRoleController {
    @Autowired
    private UnimallRoleService unimallRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallrole:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallRoleService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallrole:info")
    public R info(@PathVariable("id") Long id){
        UnimallRole unimallRole = (UnimallRole)unimallRoleService.getById(id);

        return R.ok().put("unimallRole", unimallRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallrole:save")
    public R save(@RequestBody UnimallRole unimallRole){
        unimallRoleService.save(unimallRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallrole:update")
    public R update(@RequestBody UnimallRole unimallRole){
        ValidatorUtils.validateEntity(unimallRole);
        unimallRoleService.updateById(unimallRole);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallrole:delete")
    public R delete(@RequestBody Long[] ids){
        unimallRoleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
