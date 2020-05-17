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
import com.icloud.modules.unimall.entity.UnimallImg;
import com.icloud.modules.unimall.service.UnimallImgService;
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
 * 菜单主连接： modules/unimall/unimallimg.html
 */
@RestController
@RequestMapping("unimall/unimallimg")
public class UnimallImgController {
    @Autowired
    private UnimallImgService unimallImgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallimg:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallImgService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallimg:info")
    public R info(@PathVariable("id") Long id){
        UnimallImg unimallImg = (UnimallImg)unimallImgService.getById(id);

        return R.ok().put("unimallImg", unimallImg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallimg:save")
    public R save(@RequestBody UnimallImg unimallImg){
        unimallImgService.save(unimallImg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallimg:update")
    public R update(@RequestBody UnimallImg unimallImg){
        ValidatorUtils.validateEntity(unimallImg);
        unimallImgService.updateById(unimallImg);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallimg:delete")
    public R delete(@RequestBody Long[] ids){
        unimallImgService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
