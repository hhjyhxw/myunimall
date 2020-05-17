package com.icloud.modules.unimall.controller;

import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;
import com.icloud.modules.unimall.entity.UnimallAddress;
import com.icloud.modules.unimall.service.UnimallAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:02
 * 菜单主连接： modules/unimall/unimalladdress.html
 */
@RestController
@RequestMapping("unimall/unimalladdress")
public class UnimallAddressController {
    @Autowired
    private UnimallAddressService unimallAddressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimalladdress:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallAddressService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimalladdress:info")
    public R info(@PathVariable("id") Long id){
        UnimallAddress unimallAddress = (UnimallAddress)unimallAddressService.getById(id);

        return R.ok().put("unimallAddress", unimallAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimalladdress:save")
    public R save(@RequestBody UnimallAddress unimallAddress){
        unimallAddressService.save(unimallAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimalladdress:update")
    public R update(@RequestBody UnimallAddress unimallAddress){
        ValidatorUtils.validateEntity(unimallAddress);
        unimallAddressService.updateById(unimallAddress);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimalladdress:delete")
    public R delete(@RequestBody Long[] ids){
        unimallAddressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
