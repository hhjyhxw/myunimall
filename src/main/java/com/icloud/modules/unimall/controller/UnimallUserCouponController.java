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
import com.icloud.modules.unimall.entity.UnimallUserCoupon;
import com.icloud.modules.unimall.service.UnimallUserCouponService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 用户优惠卷表
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 * 菜单主连接： modules/unimall/unimallusercoupon.html
 */
@RestController
@RequestMapping("unimall/unimallusercoupon")
public class UnimallUserCouponController {
    @Autowired
    private UnimallUserCouponService unimallUserCouponService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallusercoupon:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallUserCouponService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallusercoupon:info")
    public R info(@PathVariable("id") Long id){
        UnimallUserCoupon unimallUserCoupon = (UnimallUserCoupon)unimallUserCouponService.getById(id);

        return R.ok().put("unimallUserCoupon", unimallUserCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallusercoupon:save")
    public R save(@RequestBody UnimallUserCoupon unimallUserCoupon){
        unimallUserCouponService.save(unimallUserCoupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallusercoupon:update")
    public R update(@RequestBody UnimallUserCoupon unimallUserCoupon){
        ValidatorUtils.validateEntity(unimallUserCoupon);
        unimallUserCouponService.updateById(unimallUserCoupon);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallusercoupon:delete")
    public R delete(@RequestBody Long[] ids){
        unimallUserCouponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
