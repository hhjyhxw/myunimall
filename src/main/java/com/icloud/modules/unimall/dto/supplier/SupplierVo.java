package com.icloud.modules.unimall.dto.supplier;

import com.icloud.modules.unimall.dto.goods.GoodsVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierVo {
    private Long id;
    private String companyName;
    private String address;
    private String contactPhone;
    private String businessHours;
    private String supplierImg;
    private List<GoodsVo> goodsList = new ArrayList<GoodsVo>();
}
