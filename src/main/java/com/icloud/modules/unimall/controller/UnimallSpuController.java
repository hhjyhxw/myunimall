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
import com.icloud.modules.unimall.entity.UnimallSpu;
import com.icloud.modules.unimall.service.UnimallSpuService;
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
 * 菜单主连接： modules/unimall/unimallspu.html
 */
@RestController
@RequestMapping("unimall/unimallspu")
public class UnimallSpuController {
    @Autowired
    private UnimallSpuService unimallSpuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("unimall:unimallspu:list")
    public R list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        PageUtils page = unimallSpuService.findByPage(query.getPageNum(),query.getPageSize(), query);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("unimall:unimallspu:info")
    public R info(@PathVariable("id") Long id){
        UnimallSpu unimallSpu = (UnimallSpu)unimallSpuService.getById(id);

        return R.ok().put("unimallSpu", unimallSpu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("unimall:unimallspu:save")
    public R save(@RequestBody UnimallSpu unimallSpu){
        unimallSpuService.save(unimallSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("unimall:unimallspu:update")
    public R update(@RequestBody UnimallSpu unimallSpu){
        ValidatorUtils.validateEntity(unimallSpu);
        unimallSpuService.updateById(unimallSpu);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("unimall:unimallspu:delete")
    public R delete(@RequestBody Long[] ids){
        unimallSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
