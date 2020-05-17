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
import com.icloud.modules.unimall.entity.UnimallUser;
import com.icloud.modules.unimall.service.UnimallUserService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 * 菜单主连接： modules/unimall/unimalluser.html
 */
@RestController
@RequestMapping("unimall/unimalluser")
public class UnimallUserController {
    @Autowired
    private UnimallUserService unimallUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimalluser:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallUserService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimalluser:info")
    public R info(@PathVariable("id") Long id){
        UnimallUser unimallUser = (UnimallUser)unimallUserService.getById(id);

        return R.ok().put("unimallUser", unimallUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimalluser:save")
    public R save(@RequestBody UnimallUser unimallUser){
        unimallUserService.save(unimallUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimalluser:update")
    public R update(@RequestBody UnimallUser unimallUser){
        ValidatorUtils.validateEntity(unimallUser);
        unimallUserService.updateById(unimallUser);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimalluser:delete")
    public R delete(@RequestBody Long[] ids){
        unimallUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
