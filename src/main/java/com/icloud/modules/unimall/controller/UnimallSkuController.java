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
import com.icloud.modules.unimall.entity.UnimallSku;
import com.icloud.modules.unimall.service.UnimallSkuService;
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
 * 菜单主连接： modules/unimall/unimallsku.html
 */
@RestController
@RequestMapping("unimall/unimallsku")
public class UnimallSkuController {
    @Autowired
    private UnimallSkuService unimallSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallsku:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallSkuService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallsku:info")
    public R info(@PathVariable("id") Long id){
        UnimallSku unimallSku = (UnimallSku)unimallSkuService.getById(id);

        return R.ok().put("unimallSku", unimallSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallsku:save")
    public R save(@RequestBody UnimallSku unimallSku){
        unimallSkuService.save(unimallSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallsku:update")
    public R update(@RequestBody UnimallSku unimallSku){
        ValidatorUtils.validateEntity(unimallSku);
        unimallSkuService.updateById(unimallSku);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallsku:delete")
    public R delete(@RequestBody Long[] ids){
        unimallSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
