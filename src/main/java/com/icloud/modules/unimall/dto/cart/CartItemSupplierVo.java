package com.icloud.modules.unimall.dto.cart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartItemSupplierVo {

    private Long id;//商户id
    private String supplierName;//店铺名称
    private String supplierAddress;//店铺地址
    private String supplierImg;//店铺图片
    private BigDecimal totalAmount;//商品总金额
    private Integer totalNum;//商品总件数
    private List<CarItemVo> careItemList = new ArrayList<CarItemVo>();
    private boolean checked = true;//购物车中 是否选中
}
