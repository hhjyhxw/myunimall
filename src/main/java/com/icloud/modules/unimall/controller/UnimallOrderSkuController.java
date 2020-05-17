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
import com.icloud.modules.unimall.entity.UnimallOrderSku;
import com.icloud.modules.unimall.service.UnimallOrderSkuService;
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
 * 菜单主连接： modules/unimall/unimallordersku.html
 */
@RestController
@RequestMapping("unimall/unimallordersku")
public class UnimallOrderSkuController {
    @Autowired
    private UnimallOrderSkuService unimallOrderSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallordersku:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallOrderSkuService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallordersku:info")
    public R info(@PathVariable("id") Long id){
        UnimallOrderSku unimallOrderSku = (UnimallOrderSku)unimallOrderSkuService.getById(id);

        return R.ok().put("unimallOrderSku", unimallOrderSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallordersku:save")
    public R save(@RequestBody UnimallOrderSku unimallOrderSku){
        unimallOrderSkuService.save(unimallOrderSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallordersku:update")
    public R update(@RequestBody UnimallOrderSku unimallOrderSku){
        ValidatorUtils.validateEntity(unimallOrderSku);
        unimallOrderSkuService.updateById(unimallOrderSku);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallordersku:delete")
    public R delete(@RequestBody Long[] ids){
        unimallOrderSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
