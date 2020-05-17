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
import com.icloud.modules.unimall.entity.UnimallCategory;
import com.icloud.modules.unimall.service.UnimallCategoryService;
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
 * 菜单主连接： modules/unimall/unimallcategory.html
 */
@RestController
@RequestMapping("unimall/unimallcategory")
public class UnimallCategoryController {
    @Autowired
    private UnimallCategoryService unimallCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallcategory:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallCategoryService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallcategory:info")
    public R info(@PathVariable("id") Long id){
        UnimallCategory unimallCategory = (UnimallCategory)unimallCategoryService.getById(id);

        return R.ok().put("unimallCategory", unimallCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallcategory:save")
    public R save(@RequestBody UnimallCategory unimallCategory){
        unimallCategoryService.save(unimallCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallcategory:update")
    public R update(@RequestBody UnimallCategory unimallCategory){
        ValidatorUtils.validateEntity(unimallCategory);
        unimallCategoryService.updateById(unimallCategory);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallcategory:delete")
    public R delete(@RequestBody Long[] ids){
        unimallCategoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
