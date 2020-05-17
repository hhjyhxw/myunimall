package com.icloud.modules.unimall.utils;

import com.icloud.modules.unimall.dto.cart.CarItemVo;
import com.icloud.modules.unimall.dto.cart.CartItemSupplierVo;
import com.icloud.modules.unimall.entity.UnimallCart;
import com.icloud.modules.unimall.service.UnimallSkuService;
import com.icloud.modules.unimall.service.UnimallSpuService;
import com.icloud.modules.unimall.service.UnimallSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CartItemOrderUtils {

    @Autowired
    private UnimallSkuService unimallSkuService;
    @Autowired
    private UnimallSpuService unimallSpuService;
    @Autowired
    private UnimallSupplierService unimallSupplierService;

    public  Map<String, CartItemSupplierVo> mergeBySupplier(List<UnimallCart> list) {

        /*
            按店铺分组整理构成车信息
         */
        Map<String,CartItemSupplierVo> map = new HashMap<String,CartItemSupplierVo>();
        for (UnimallCart ci : list) {
            CartItemSupplierVo cartItemSupplierVo = map.get(ci.getSupplier().getId().toString());
//            UnimallSku sku = (UnimallSku) unimallSkuService.getOne(new QueryWrapper<UnimallSku>().eq("id",ci.getSkuId()));
//            UnimallSpu spu = (UnimallSpu) unimallSupplierService.getOne(new QueryWrapper<UnimallSpu>().eq("id",sku.getSpuId()));
//            UnimallSupplier supplier = (UnimallSupplier) unimallSupplierService.getOne(new QueryWrapper<UnimallSupplier>().eq("id",spu.getSupplierId()));
            if(cartItemSupplierVo==null){
                cartItemSupplierVo = new CartItemSupplierVo();
                cartItemSupplierVo.setSupplierName(ci.getSupplier().getShopName());
                cartItemSupplierVo.setSupplierImg(ci.getSupplier().getShopIcon());
                cartItemSupplierVo.setId(ci.getSupplier().getId());
                cartItemSupplierVo.setSupplierAddress(ci.getSupplier().getAddress());
                cartItemSupplierVo.setTotalAmount(new BigDecimal(ci.getNum()*ci.getSku().getPrice()/100));//
                cartItemSupplierVo.setTotalNum(ci.getNum());
            }else{
                cartItemSupplierVo.setTotalAmount(cartItemSupplierVo.getTotalAmount().add(new BigDecimal(ci.getNum()*ci.getSku().getPrice()/100)));//
                cartItemSupplierVo.setTotalNum(cartItemSupplierVo.getTotalNum()+ci.getNum());
            }

            CarItemVo vo = new CarItemVo();
            vo.setTotalAmount(new BigDecimal(ci.getNum()*ci.getSku().getPrice()/100));
            vo.setPrice(new BigDecimal(ci.getSku().getPrice()/100));
            vo.setListImgPath(ci.getSku().getImg());
            vo.setPName(ci.getSku().getTitle());
            vo.setQuantity(ci.getNum());
            vo.setProductId(ci.getSku().getId());
            vo.setGoodsId(ci.getSku().getId());
            cartItemSupplierVo.getCareItemList().add(vo);
            map.put(ci.getSupplier().getId().toString(), cartItemSupplierVo);
        }
        return map;
    }

    /**
     * 获取用户选择的 购物车记录
     */
    public  List<UnimallCart> getNewCartList(List<UnimallCart> cartList,String[] pids){
        List<UnimallCart> newList = new ArrayList<UnimallCart>();
        for(UnimallCart carItem:cartList){
            for(String pid:pids){
                if(carItem.getSkuId().toString().equals(pid)){
                    newList.add(carItem);
                    continue;
                }
            }
        }
        return newList;
    }

    public  BigDecimal getTotalMoney(List<UnimallCart> list) {
        BigDecimal toatalMoney = new BigDecimal(0);
        for (UnimallCart temp:list){
            toatalMoney = toatalMoney.add(new BigDecimal(temp.getNum()*temp.getSku().getPrice()/100));
        }
        return toatalMoney;
    }


    public  Integer getTotalQuantitys(List<UnimallCart> list) {
        Integer toatalQuantitys = 0;
        for (UnimallCart temp:list){
            toatalQuantitys+=temp.getNum();
        }
        return toatalQuantitys;
    }

    /**
     * 匹配是否包含数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public  boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
