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
import com.icloud.modules.unimall.entity.UnimallAppraise;
import com.icloud.modules.unimall.service.UnimallAppraiseService;
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
 * 菜单主连接： modules/unimall/unimallappraise.html
 */
@RestController
@RequestMapping("unimall/unimallappraise")
public class UnimallAppraiseController {
    @Autowired
    private UnimallAppraiseService unimallAppraiseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallappraise:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallAppraiseService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallappraise:info")
    public R info(@PathVariable("id") Long id){
        UnimallAppraise unimallAppraise = (UnimallAppraise)unimallAppraiseService.getById(id);

        return R.ok().put("unimallAppraise", unimallAppraise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallappraise:save")
    public R save(@RequestBody UnimallAppraise unimallAppraise){
        unimallAppraiseService.save(unimallAppraise);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallappraise:update")
    public R update(@RequestBody UnimallAppraise unimallAppraise){
        ValidatorUtils.validateEntity(unimallAppraise);
        unimallAppraiseService.updateById(unimallAppraise);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallappraise:delete")
    public R delete(@RequestBody Long[] ids){
        unimallAppraiseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
