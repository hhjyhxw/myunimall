package com.icloud.modules.unimall.dto.goods;

import com.icloud.modules.unimall.dto.supplier.SupplierVo;
import com.icloud.modules.unimall.entity.UnimallSku;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsVo {
    private Long id;//id
    private Long pid;//productId
    private String name;//商品名称
    private BigDecimal marketPrice;//商品市场价
    private BigDecimal originalPrice;//商品原价
    private Integer store;//总库存
    private Integer freezeStore;//冻结库存
    private Integer salesCount;//销量
    private Integer monthSalesCount;//月销量
    private String promotionInfo;
    private String promoteMessage;
    private String producingArea;//产地
    private String introduction;//商品详细
    private String defaultSourceImagePath;//商品头图
    private SupplierVo supplier;//店铺对象
    private Boolean isMaiwan = false;//释放已售完

    private List<UnimallSku> skuList;//后续扩展修改（暂时不需要 2020-05-17）

}
