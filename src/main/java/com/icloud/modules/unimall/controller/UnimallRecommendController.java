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
import com.icloud.modules.unimall.entity.UnimallRecommend;
import com.icloud.modules.unimall.service.UnimallRecommendService;
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
 * 菜单主连接： modules/unimall/unimallrecommend.html
 */
@RestController
@RequestMapping("unimall/unimallrecommend")
public class UnimallRecommendController {
    @Autowired
    private UnimallRecommendService unimallRecommendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallrecommend:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallRecommendService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallrecommend:info")
    public R info(@PathVariable("id") Long id){
        UnimallRecommend unimallRecommend = (UnimallRecommend)unimallRecommendService.getById(id);

        return R.ok().put("unimallRecommend", unimallRecommend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallrecommend:save")
    public R save(@RequestBody UnimallRecommend unimallRecommend){
        unimallRecommendService.save(unimallRecommend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallrecommend:update")
    public R update(@RequestBody UnimallRecommend unimallRecommend){
        ValidatorUtils.validateEntity(unimallRecommend);
        unimallRecommendService.updateById(unimallRecommend);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallrecommend:delete")
    public R delete(@RequestBody Long[] ids){
        unimallRecommendService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
