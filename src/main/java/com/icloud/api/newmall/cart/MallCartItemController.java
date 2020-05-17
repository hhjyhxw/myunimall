package com.icloud.api.newmall.cart;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icloud.annotation.LoginUser;
import com.icloud.basecommon.model.MapRespone;
import com.icloud.modules.unimall.dto.cart.CartItemSupplierVo;
import com.icloud.modules.unimall.entity.UnimallCart;
import com.icloud.modules.unimall.entity.UnimallSku;
import com.icloud.modules.unimall.entity.UnimallSpu;
import com.icloud.modules.unimall.entity.UnimallUser;
import com.icloud.modules.unimall.service.UnimallCartService;
import com.icloud.modules.unimall.service.UnimallSkuService;
import com.icloud.modules.unimall.service.UnimallSpuService;
import com.icloud.modules.unimall.utils.CartItemOrderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/mall/cartitem")
public class MallCartItemController {

    private final Logger logger = LoggerFactory.getLogger(MallCartItemController.class);
    @Autowired
    private UnimallCartService unimallCartService;
    @Autowired
    private CartItemOrderUtils cartItemOrderUtils;
    @Autowired
    private UnimallSkuService unimallSkuService;
    @Autowired
    private UnimallSpuService unimallSpuService;
    /**
     * 购物车列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public MapRespone cartItemList(@LoginUser UnimallUser user){
//        List<UnimallCart> list = unimallCartService.list(new QueryWrapper<UnimallCart>()
//                .eq("user_id", user.getId()));
        UnimallCart cart = new UnimallCart();
        cart.setUserId(user.getId());
        List<UnimallCart> list =unimallCartService.queryCartItemSupplierVoList(cart);
        logger.info("cartllist.size==="+ (list!=null?list.size():null));
        //按供应商分组
        Map<String, CartItemSupplierVo> resultMap = cartItemOrderUtils.mergeBySupplier(list);
        logger.info("resultMap==="+ JSON.toJSONString(resultMap));
        List<CartItemSupplierVo> listvo = new ArrayList<CartItemSupplierVo>();
        for(Map.Entry<String, CartItemSupplierVo> entry : resultMap.entrySet()){
            String mapKey = entry.getKey();
            CartItemSupplierVo mapValue = entry.getValue();
            listvo.add(mapValue);
        }
        return MapRespone.ok().put("list",listvo);

    }



    /**
     * 检查库存
     * @return
     * ids 购物车id
     */
    @RequestMapping("/checkStore")
    @ResponseBody
    public MapRespone checkStore(@RequestBody Long[] ids) {
        logger.info("checkStore:param==="+ JSON.toJSONString(ids));
        UnimallCart cart = new UnimallCart();
        cart.setIds(ids);
        List<UnimallCart> list = unimallCartService.queryCartItemSupplierVoList(cart);
        for(UnimallCart temp:list){
            if (temp.getSpu().getStatus().intValue()==0) {
                return MapRespone.error(temp.getSku().getTitle()+"已下架");
            }
            int kuncun = temp.getSku().getFreezeStock()!=null?temp.getSku().getStock()-temp.getSku().getFreezeStock():temp.getSku().getStock();
            if (temp.getNum() > kuncun) {
                return MapRespone.error(temp.getSku().getTitle()+"库存不足");
            }
        }
        return MapRespone.ok();

    }

    /**
     * 清空购物车
     *
     * @param  pids 商品id
     * @return
     */
    @RequestMapping("/deleteCarItems")
    @ResponseBody
    public MapRespone deleteCarItems(@RequestBody Long[] pids,@LoginUser UnimallUser user) {
        logger.info("deleteCarItems:pids==="+ JSON.toJSONString(pids));
       boolean result =  unimallCartService.remove(new QueryWrapper<UnimallCart>()
                .eq("user_id",user.getId())
                .in("sku_id",pids));
        return result==true?MapRespone.ok():MapRespone.error();

    }

    /**
     * 减商品
     * @param pid sku_id
     * @param user
     * @return
     */
    @RequestMapping("/ajaxMinuses")
    @ResponseBody
    public MapRespone ajaxMinuses(Long pid,@LoginUser UnimallUser user) {
        logger.info("ajaxMinuses:pid==="+ pid);
        if (pid==null || pid<=0){
            return MapRespone.error("参数为空");
        }
        UnimallCart carItem = (UnimallCart) unimallCartService.getOne(new QueryWrapper<UnimallCart>()
                .eq("user_id", user.getId())
                .eq("sku_id", pid));
        boolean result = false;
        if (carItem != null) {
            int num = carItem.getNum() - 1;
            if (num > 0) {
                carItem.setNum(num);
                carItem.setGmtUpdate(new Date());
                result = unimallCartService.updateById(carItem);
            } else {
                result =unimallCartService.removeById(carItem);
            }
        }
        return result==true?MapRespone.ok():MapRespone.error();

    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("/ajaxAdd")
    @ResponseBody
    public MapRespone ajaxAdd(Long pid,@LoginUser UnimallUser user) {
        logger.info("ajaxMinuses:pid==="+ pid);
        if (pid==null || pid<=0){
            return MapRespone.error("参数为空");
        }
        UnimallSku sku = new UnimallSku();sku.setId(pid);
        sku =(UnimallSku)unimallSkuService.getById(sku);

        UnimallSpu spu = new UnimallSpu();spu.setId(sku.getSpuId());
        spu =(UnimallSpu)unimallSpuService.getById(spu);
        if (spu.getStatus().intValue()==0) {
            return MapRespone.error(sku.getTitle()+"已下架");
        }
        int kuncun = sku.getFreezeStock()!=null?sku.getStock()-sku.getFreezeStock():sku.getStock();
        if (1 > kuncun) {
            return MapRespone.error(sku.getTitle()+"库存不足");
        }
        UnimallCart carItem = (UnimallCart) unimallCartService.getOne(new QueryWrapper<UnimallCart>()
                .eq("user_id", user.getId())
                .eq("sku_id", pid));

        boolean result = false;
        if (carItem != null) {
            int num = carItem.getNum() + 1;
            carItem.setNum(num);
            carItem.setGmtUpdate(new Date());
            result = unimallCartService.updateById(carItem);
        }else{
            carItem = getCarItem(pid,user.getId(),1);
            result = unimallCartService.save(carItem);
        }
        return result==true?MapRespone.ok():MapRespone.error();
    }

    /**
     * 文本框修改购物数量
     * @return
     */
    @RequestMapping("/updatecarItem")
    @ResponseBody
    public MapRespone updatecarItem(Long pid,Integer quantities,@LoginUser UnimallUser user) {
        logger.info("ajaxMinuses:pid==="+ pid+" ;quantities==="+quantities);
        if (pid==null || pid<=0 || quantities==null || quantities<=0){
            return MapRespone.error("参数不正确");
        }
        UnimallSku sku = new UnimallSku();sku.setId(pid);
        sku =(UnimallSku)unimallSkuService.getById(sku);

        UnimallSpu spu = new UnimallSpu();spu.setId(sku.getSpuId());
        spu =(UnimallSpu)unimallSpuService.getById(spu);
        if (spu.getStatus().intValue()==0) {
            return MapRespone.error(sku.getTitle()+"已下架");
        }
        //剩余库存
        int kuncun = sku.getFreezeStock()!=null?sku.getStock()-sku.getFreezeStock():sku.getStock();


        UnimallCart carItem = (UnimallCart) unimallCartService.getOne(new QueryWrapper<UnimallCart>()
                .eq("user_id", user.getId())
                .eq("sku_id", pid));

        boolean result = false;
        if (carItem != null) {
            if (carItem.getNum() > quantities) {//传入的数量小于购物车原有数量
                carItem.setNum(quantities);
            } else if (carItem.getNum() < quantities) {
                if (quantities > kuncun) {
                    return MapRespone.error(sku.getTitle()+"库存不足");
                }
                carItem.setNum(quantities);
            }
            carItem.setGmtUpdate(new Date());
            result = unimallCartService.updateById(carItem);
        }else{
            if (quantities > kuncun) {
                return MapRespone.error(sku.getTitle()+"库存不足");
            }
            carItem = getCarItem(pid,user.getId(),quantities);
            result = unimallCartService.save(carItem);
        }
        return result==true?MapRespone.ok():MapRespone.error();

    }

    /**
     * 获取购物车总数
     * @return
     */
    @RequestMapping("/getCartNum")
    @ResponseBody
    public MapRespone getCartNum(@LoginUser UnimallUser user){
                List<UnimallCart> list = unimallCartService.list(new QueryWrapper<UnimallCart>()
                .eq("user_id", user.getId()));
            int cartNum = cartItemOrderUtils.getTotalQuantitys(list);
            return MapRespone.ok().put("cartNum",cartNum);
    }

    /**
     * 封装购物篮
     *
     * @param pid
     * @return
     */
    private UnimallCart getCarItem(Long pid, Long userId,Integer num) {
        UnimallCart carItem = new UnimallCart();
        carItem.setUserId(userId);
        carItem.setSkuId(pid);
        carItem.setGmtCreate(new Date());
        carItem.setNum(num);
        return carItem;
    }
}
