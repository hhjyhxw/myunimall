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
import com.icloud.modules.unimall.entity.UnimallCollect;
import com.icloud.modules.unimall.service.UnimallCollectService;
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
 * 菜单主连接： modules/unimall/unimallcollect.html
 */
@RestController
@RequestMapping("unimall/unimallcollect")
public class UnimallCollectController {
    @Autowired
    private UnimallCollectService unimallCollectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallcollect:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallCollectService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallcollect:info")
    public R info(@PathVariable("id") Long id){
        UnimallCollect unimallCollect = (UnimallCollect)unimallCollectService.getById(id);

        return R.ok().put("unimallCollect", unimallCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallcollect:save")
    public R save(@RequestBody UnimallCollect unimallCollect){
        unimallCollectService.save(unimallCollect);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallcollect:update")
    public R update(@RequestBody UnimallCollect unimallCollect){
        ValidatorUtils.validateEntity(unimallCollect);
        unimallCollectService.updateById(unimallCollect);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallcollect:delete")
    public R delete(@RequestBody Long[] ids){
        unimallCollectService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
