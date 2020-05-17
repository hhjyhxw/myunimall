package com.icloud.api.newmall.cart;

import com.alibaba.fastjson.JSON;
import com.icloud.annotation.LoginUser;
import com.icloud.basecommon.model.MapRespone;
import com.icloud.modules.unimall.dto.cart.CartItemSupplierVo;
import com.icloud.modules.unimall.entity.UnimallCart;
import com.icloud.modules.unimall.entity.UnimallUser;
import com.icloud.modules.unimall.service.UnimallCartService;
import com.icloud.modules.unimall.service.UnimallSkuService;
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
     * @return
     */
    @RequestMapping("/deleteCarItems")
    @ResponseBody
    public MapRespone deleteCarItems() {
        return null;
    }

    /**
     * 减商品
     *
     * @return
     */
    @RequestMapping("/ajaxMinuses")
    @ResponseBody
    public MapRespone ajaxMinuses() {
        return null;
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping("/ajaxAdd")
    @ResponseBody
    public MapRespone ajaxAdd() {
        return null;
    }

    /**
     * 文本框修改购物数量
     * @return
     */
    @RequestMapping("/updatecarItem")
    @ResponseBody
    public MapRespone updatecarItem() {
        return null;
    }

    /**
     * 获取购物车总数
     * @return
     */
    @RequestMapping("/getCartNum")
    @ResponseBody
    public MapRespone getCartNum(){
        return null;
    }

    /**
     * 封装购物篮
     *
     * @param pid
     * @return
     */
    private UnimallCart getCarItem(Long pid, Long userId) {
        UnimallCart carItem = new UnimallCart();
        carItem.setUserId(userId);
        carItem.setSkuId(pid);
        carItem.setGmtCreate(new Date());
        carItem.setNum(1);
        return carItem;
    }
}
