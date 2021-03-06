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
import com.icloud.modules.unimall.entity.UnimallFreightemplate;
import com.icloud.modules.unimall.service.UnimallFreightemplateService;
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
 * 菜单主连接： modules/unimall/unimallfreightemplate.html
 */
@RestController
@RequestMapping("unimall/unimallfreightemplate")
public class UnimallFreightemplateController {
    @Autowired
    private UnimallFreightemplateService unimallFreightemplateService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallfreightemplate:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallFreightemplateService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallfreightemplate:info")
    public R info(@PathVariable("id") Long id){
        UnimallFreightemplate unimallFreightemplate = (UnimallFreightemplate)unimallFreightemplateService.getById(id);

        return R.ok().put("unimallFreightemplate", unimallFreightemplate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallfreightemplate:save")
    public R save(@RequestBody UnimallFreightemplate unimallFreightemplate){
        unimallFreightemplateService.save(unimallFreightemplate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallfreightemplate:update")
    public R update(@RequestBody UnimallFreightemplate unimallFreightemplate){
        ValidatorUtils.validateEntity(unimallFreightemplate);
        unimallFreightemplateService.updateById(unimallFreightemplate);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallfreightemplate:delete")
    public R delete(@RequestBody Long[] ids){
        unimallFreightemplateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
