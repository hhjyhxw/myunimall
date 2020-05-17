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
import com.icloud.modules.unimall.entity.UnimallPayment;
import com.icloud.modules.unimall.service.UnimallPaymentService;
import com.icloud.basecommon.model.Query;
import com.icloud.common.PageUtils;
import com.icloud.common.R;
import com.icloud.common.validator.ValidatorUtils;


/**
 * 支付流水表 支付流水表
 *
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 * 菜单主连接： modules/unimall/unimallpayment.html
 */
@RestController
@RequestMapping("unimall/unimallpayment")
public class UnimallPaymentController {
    @Autowired
    private UnimallPaymentService unimallPaymentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallpayment:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallPaymentService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallpayment:info")
    public R info(@PathVariable("id") Long id){
        UnimallPayment unimallPayment = (UnimallPayment)unimallPaymentService.getById(id);

        return R.ok().put("unimallPayment", unimallPayment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallpayment:save")
    public R save(@RequestBody UnimallPayment unimallPayment){
        unimallPaymentService.save(unimallPayment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallpayment:update")
    public R update(@RequestBody UnimallPayment unimallPayment){
        ValidatorUtils.validateEntity(unimallPayment);
        unimallPaymentService.updateById(unimallPayment);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallpayment:delete")
    public R delete(@RequestBody Long[] ids){
        unimallPaymentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
