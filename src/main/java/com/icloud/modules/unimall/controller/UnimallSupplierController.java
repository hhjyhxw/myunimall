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
import com.icloud.modules.unimall.entity.UnimallSupplier;
import com.icloud.modules.unimall.service.UnimallSupplierService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 商户表 商户表
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 * 菜单主连接： modules/unimall/unimallsupplier.html
 */
@RestController
@RequestMapping("unimall/unimallsupplier")
public class UnimallSupplierController {
    @Autowired
    private UnimallSupplierService unimallSupplierService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallsupplier:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallSupplierService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallsupplier:info")
    public R info(@PathVariable("id") Long id){
        UnimallSupplier unimallSupplier = (UnimallSupplier)unimallSupplierService.getById(id);

        return R.ok().put("unimallSupplier", unimallSupplier);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallsupplier:save")
    public R save(@RequestBody UnimallSupplier unimallSupplier){
        unimallSupplierService.save(unimallSupplier);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallsupplier:update")
    public R update(@RequestBody UnimallSupplier unimallSupplier){
        ValidatorUtils.validateEntity(unimallSupplier);
        unimallSupplierService.updateById(unimallSupplier);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallsupplier:delete")
    public R delete(@RequestBody Long[] ids){
        unimallSupplierService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
