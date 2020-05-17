package com.icloud.api.newmall.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icloud.annotation.AuthIgnore;
import com.icloud.basecommon.model.MapRespone;
import com.icloud.basecommon.model.Query;
import com.icloud.basecommon.web.AppBaseController;
import com.icloud.common.PageUtils;
import com.icloud.modules.unimall.dto.goods.GoodsVo;
import com.icloud.modules.unimall.dto.supplier.SupplierVo;
import com.icloud.modules.unimall.entity.UnimallCart;
import com.icloud.modules.unimall.entity.UnimallSku;
import com.icloud.modules.unimall.entity.UnimallSpu;
import com.icloud.modules.unimall.entity.UnimallSupplier;
import com.icloud.modules.unimall.service.UnimallSkuService;
import com.icloud.modules.unimall.service.UnimallSpuService;
import com.icloud.modules.unimall.service.UnimallSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/mall/goods")
public class MallGoodsController extends AppBaseController {

    @Autowired
    private UnimallSpuService unimallSpuService;
    @Autowired
    private UnimallSkuService unimallSkuService;
    @Autowired
    private UnimallSupplierService unimallSupplierService;
    /**
     * 商品详情
     * @return
     */
    @AuthIgnore
    @RequestMapping("/goodDetail")
    @ResponseBody
    public MapRespone goodDetail(Long id){
        logger.info("ajaxMinuses:id==="+ id);
        if (id==null || id<=0){
            return MapRespone.error("参数为空");
        }
        UnimallSpu goods = new UnimallSpu();
        goods.setId(id);
        goods = (UnimallSpu)unimallSpuService.getById(goods);
        List<UnimallSku> skuList = unimallSkuService.list(new QueryWrapper<UnimallCart>()
                .eq("spu_id", goods.getId()));
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setId(goods.getId());
        goodsVo.setDefaultSourceImagePath(goods.getImg());

        int freeStore = 0;
        int store = 0;

        for(UnimallSku sku : skuList){
            freeStore+=sku.getFreezeStock();
            store+=sku.getStock();
        }
        if(store<=freeStore){
            goodsVo.setIsMaiwan(true);
        }
        goodsVo.setFreezeStore(freeStore);
        goodsVo.setStore(store);
        goodsVo.setSalesCount(goods.getSales());
        goodsVo.setIntroduction("");//后续添加
        goodsVo.setMarketPrice(new BigDecimal(goods.getPrice()));
        goodsVo.setOriginalPrice(new BigDecimal(goods.getOriginalPrice()/100));
        goodsVo.setPid(skuList.get(0).getId());
        goodsVo.setSkuList(skuList);
        goodsVo.setName(goods.getTitle());
        goodsVo.setIntroduction(goods.getDetail());
        goodsVo.setProducingArea("");
        goodsVo.setPromotionInfo("");
        goodsVo.setPromoteMessage("");

        goodsVo.setMonthSalesCount(0);

        //复制店铺属性
        UnimallSupplier supplier = new UnimallSupplier();
        supplier.setId(goods.getSupplierId());
        supplier = (UnimallSupplier)unimallSupplierService.getById(supplier);
        SupplierVo supplierVo = new SupplierVo();
        supplierVo.setAddress(supplier.getAddress());
        supplierVo.setBusinessHours(supplier.getBusinessHours());
        supplierVo.setCompanyName(supplier.getShopName());
        supplierVo.setContactPhone(supplier.getPhone());
        supplierVo.setSupplierImg(supplier.getShopIcon());
        supplierVo.setId(supplier.getId());
        goodsVo.setSupplier(supplierVo);

        return MapRespone.ok().put("good",goodsVo);
    }

    /**
     * 异步获取
     * 获取首页 优选商品  取 最近修改或者添加的 优选商品  order by modifydate desc
     */
    @AuthIgnore
    @RequestMapping("/getPerfectGoodsList")
    @ResponseBody
    public MapRespone getPerfectGoodsList(Integer pageSize, Integer pageNo) {
        Query query = new Query(new HashMap<>());
        query.put("isSelect", 1);//优选商品
        PageUtils page = unimallSpuService.findByPage(pageNo != null ? pageNo : 1, pageSize != null ? pageSize : 20, query);
        List<UnimallSpu> list = page.getList();
        List<GoodsVo> newList = new ArrayList<GoodsVo>();
        if (list != null && list.size() > 0) {
            for (UnimallSpu goods : list) {
                GoodsVo vo = new GoodsVo();
                if (goods.getPrice() != null) {
                    vo.setMarketPrice(new BigDecimal(goods.getPrice() / 100));
                }
                vo.setDefaultSourceImagePath(goods.getImg());
                vo.setName(goods.getTitle());
                vo.setId(goods.getId());
                newList.add(vo);
            }
        }
        return MapRespone.ok().put("list", newList).put("total", page.getTotalPage());
    }
    /***
     * 异步获取广告位
     *
     * @return
     */
    @AuthIgnore
    @RequestMapping("/ajaxGetOperating")
    @ResponseBody
    public MapRespone ajaxGetOperating() {
        return null;
    }

    /***
     * 获取专题对象
     *
     * @return
     */
    @AuthIgnore
    @RequestMapping("/getZhuantDetail")
    @ResponseBody
    public MapRespone getZhuantDetail() {
        return null;
    }

    /***
     *
     * @return
     */

    /**
     * 商品搜索
     * @param pageSize 每页显示记录数
     * @param pageNo    第几页
     * @param keyword   关键字
     * @param isHot 热门
     * @param isNew 新品
     * @param isDiscount    折扣
     * @param isSelect  销量
     *  @param supplierId    商户id
     *  @param categoryId  分类id
     *  @param sales  0 销量降序 1销量升序
     * @return
     */
    @AuthIgnore
    @RequestMapping("/getGoodlistByKeyword")
    @ResponseBody
    public MapRespone getGoodlistByKeyword(@RequestParam Map<String, Object> params, Integer pageSize, Integer pageNo, String keyword,
                                           Integer isHot, Integer isNew, Integer isDiscount,
                                           Integer isSelect, Long supplierId, Long categoryId, Integer sales) {

        Query query = new Query(params);
        query.put("title",query.get("keyword"));//关键字
        PageUtils page = unimallSpuService.findByPage(pageNo != null ? pageNo : 1, pageSize != null ? pageSize : 20, query);
        List<UnimallSpu> list = page.getList();
        List<GoodsVo> newList = new ArrayList<GoodsVo>();
        if (list != null && list.size() > 0) {
            for (UnimallSpu goods : list) {
                List<UnimallSku> skuList = unimallSkuService.list(new QueryWrapper<UnimallCart>()
                        .eq("spu_id", goods.getId()));

                GoodsVo goodsVo = new GoodsVo();
                goodsVo.setId(goods.getId());
                goodsVo.setDefaultSourceImagePath(goods.getImg());

                int freeStore = 0;
                int store = 0;
                for(UnimallSku sku : skuList){
                    freeStore+=sku.getFreezeStock();
                    store+=sku.getStock();
                }
                if(store<=freeStore){
                    goodsVo.setIsMaiwan(true);
                }
                goodsVo.setFreezeStore(freeStore);
                goodsVo.setStore(store);
                goodsVo.setSalesCount(goods.getSales());
                goodsVo.setIntroduction("");//后续添加
                goodsVo.setMarketPrice(new BigDecimal(goods.getPrice()));
                goodsVo.setOriginalPrice(new BigDecimal(goods.getOriginalPrice()/100));
                goodsVo.setPid(skuList.get(0).getId());
                goodsVo.setSkuList(skuList);
                goodsVo.setName(goods.getTitle());
                goodsVo.setIntroduction(goods.getDetail());
                goodsVo.setProducingArea("");
                goodsVo.setPromotionInfo("");
                goodsVo.setPromoteMessage("");

                goodsVo.setMonthSalesCount(0);

                //复制店铺属性
                UnimallSupplier supplier = new UnimallSupplier();
                supplier.setId(goods.getSupplierId());
                supplier = (UnimallSupplier)unimallSupplierService.getById(supplier);
                SupplierVo supplierVo = new SupplierVo();
                supplierVo.setAddress(supplier.getAddress());
                supplierVo.setBusinessHours(supplier.getBusinessHours());
                supplierVo.setCompanyName(supplier.getShopName());
                supplierVo.setContactPhone(supplier.getPhone());
                supplierVo.setSupplierImg(supplier.getShopIcon());
                supplierVo.setId(supplier.getId());
                goodsVo.setSupplier(supplierVo);
                newList.add(goodsVo);
            }
        }
        return MapRespone.ok().put("list", newList).put("total", page.getTotalPage());
    }

    @AuthIgnore
    @RequestMapping("/ajaxProjectList")
    @ResponseBody
    public MapRespone ajaxProjectList() {
        return MapRespone.error("待开发");
    }
}
