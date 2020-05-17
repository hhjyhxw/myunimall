package com.icloud.modules.unimall.dto.cart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarItemVo {

    private Long id;
    private Long memberId;    //会员id
    private Long productId;    //产品id
    private Integer quantity;    //数量
    //附属展示信息或转订单信息
    private String listImgPath;    //图片路径
    private String pName;    //商品名称
    private BigDecimal price;    //价格(根据数量对应的)
    private BigDecimal totalAmount;    //每一项的金额总和
    private boolean isMarketable;//是否下架
    private long goodsId;
    private boolean checked = true;//是否被选中

}