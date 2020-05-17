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
import com.icloud.modules.unimall.entity.UnimallCoupon;
import com.icloud.modules.unimall.service.UnimallCouponService;
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
 * 菜单主连接： modules/unimall/unimallcoupon.html
 */
@RestController
@RequestMapping("unimall/unimallcoupon")
public class UnimallCouponController {
    @Autowired
    private UnimallCouponService unimallCouponService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallcoupon:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallCouponService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallcoupon:info")
    public R info(@PathVariable("id") Long id){
        UnimallCoupon unimallCoupon = (UnimallCoupon)unimallCouponService.getById(id);

        return R.ok().put("unimallCoupon", unimallCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallcoupon:save")
    public R save(@RequestBody UnimallCoupon unimallCoupon){
        unimallCouponService.save(unimallCoupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallcoupon:update")
    public R update(@RequestBody UnimallCoupon unimallCoupon){
        ValidatorUtils.validateEntity(unimallCoupon);
        unimallCouponService.updateById(unimallCoupon);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallcoupon:delete")
    public R delete(@RequestBody Long[] ids){
        unimallCouponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
