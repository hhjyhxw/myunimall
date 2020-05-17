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
import com.icloud.modules.unimall.entity.UnimallCart;
import com.icloud.modules.unimall.service.UnimallCartService;
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
 * 菜单主连接： modules/unimall/unimallcart.html
 */
@RestController
@RequestMapping("unimall/unimallcart")
public class UnimallCartController {
    @Autowired
    private UnimallCartService unimallCartService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallcart:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallCartService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallcart:info")
    public R info(@PathVariable("id") Long id){
        UnimallCart unimallCart = (UnimallCart)unimallCartService.getById(id);

        return R.ok().put("unimallCart", unimallCart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallcart:save")
    public R save(@RequestBody UnimallCart unimallCart){
        unimallCartService.save(unimallCart);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallcart:update")
    public R update(@RequestBody UnimallCart unimallCart){
        ValidatorUtils.validateEntity(unimallCart);
        unimallCartService.updateById(unimallCart);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallcart:delete")
    public R delete(@RequestBody Long[] ids){
        unimallCartService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
