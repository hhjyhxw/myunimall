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
import com.icloud.modules.unimall.entity.UnimallSpuAttribute;
import com.icloud.modules.unimall.service.UnimallSpuAttributeService;
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
 * 菜单主连接： modules/unimall/unimallspuattribute.html
 */
@RestController
@RequestMapping("unimall/unimallspuattribute")
public class UnimallSpuAttributeController {
    @Autowired
    private UnimallSpuAttributeService unimallSpuAttributeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallspuattribute:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallSpuAttributeService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallspuattribute:info")
    public R info(@PathVariable("id") Long id){
        UnimallSpuAttribute unimallSpuAttribute = (UnimallSpuAttribute)unimallSpuAttributeService.getById(id);

        return R.ok().put("unimallSpuAttribute", unimallSpuAttribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallspuattribute:save")
    public R save(@RequestBody UnimallSpuAttribute unimallSpuAttribute){
        unimallSpuAttributeService.save(unimallSpuAttribute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallspuattribute:update")
    public R update(@RequestBody UnimallSpuAttribute unimallSpuAttribute){
        ValidatorUtils.validateEntity(unimallSpuAttribute);
        unimallSpuAttributeService.updateById(unimallSpuAttribute);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallspuattribute:delete")
    public R delete(@RequestBody Long[] ids){
        unimallSpuAttributeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
