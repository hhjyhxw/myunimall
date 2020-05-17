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
import com.icloud.modules.unimall.entity.UnimallUserFormId;
import com.icloud.modules.unimall.service.UnimallUserFormIdService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 * 菜单主连接： modules/unimall/unimalluserformid.html
 */
@RestController
@RequestMapping("unimall/unimalluserformid")
public class UnimallUserFormIdController {
    @Autowired
    private UnimallUserFormIdService unimallUserFormIdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimalluserformid:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallUserFormIdService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimalluserformid:info")
    public R info(@PathVariable("id") Long id){
        UnimallUserFormId unimallUserFormId = (UnimallUserFormId)unimallUserFormIdService.getById(id);

        return R.ok().put("unimallUserFormId", unimallUserFormId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimalluserformid:save")
    public R save(@RequestBody UnimallUserFormId unimallUserFormId){
        unimallUserFormIdService.save(unimallUserFormId);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimalluserformid:update")
    public R update(@RequestBody UnimallUserFormId unimallUserFormId){
        ValidatorUtils.validateEntity(unimallUserFormId);
        unimallUserFormIdService.updateById(unimallUserFormId);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimalluserformid:delete")
    public R delete(@RequestBody Long[] ids){
        unimallUserFormIdService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
