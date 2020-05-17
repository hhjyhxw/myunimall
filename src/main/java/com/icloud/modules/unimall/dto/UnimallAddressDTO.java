package com.icloud.modules.unimall.dto;

import com.icloud.modules.unimall.entity.UnimallAddress;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 前端数据模型 适配 后端实体类
 */
@Data
public class UnimallAddressDTO {

    private Long id;
    @NotNull(message = "省份不能为空")
    private  String provinceName;
    @NotNull(message = "城市不能为空")
    private  String cityName;
    @NotNull(message = "区县不能为空")
    private  String countyName;
    private  String townName;
    @NotNull(message = "手机不能为空")
    private  String mobile;
    @NotNull(message = "收件人不能为空")
    private  String name;
    @NotNull(message = "详细地址不能为空")
    private  String address;
    @NotNull(message = "默认设置不能为空")
    private  Integer isDefault;


    public UnimallAddress getUnimallAddress(){
        UnimallAddress temp = new UnimallAddress();
        temp.setId(this.id);
        temp.setProvince(this.provinceName);
        temp.setCity(this.cityName);
        temp.setCounty(this.countyName);
        temp.setPhone(this.mobile);
        temp.setConsignee(this.name);
        temp.setAddress(this.address);
        temp.setDefaultAddress(this.isDefault);
        return temp;
    }

    public UnimallAddressDTO getUnimallAddressDTO(UnimallAddress mallAddress){
        this.id = mallAddress.getId();
        this.provinceName = mallAddress.getProvince();
        this.cityName = mallAddress.getCity();
        this.countyName = mallAddress.getCounty();
        this.mobile = mallAddress.getPhone();
        this.name = mallAddress.getConsignee();
        this.address = mallAddress.getAddress();
        this.isDefault = mallAddress.getDefaultAddress();
        return this;
    }
}
