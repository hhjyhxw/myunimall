package com.icloud.modules.unimall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 商户表 商户表
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Data
@TableName("t_unimall_supplier")
public class UnimallSupplier implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /* id */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 联系人带电话 */
       @TableField("phone")
       private String phone;
   	   	   /* 联系人 */
       @TableField("contact")
       private String contact;
   	   	   /* 店铺名称 */
       @TableField("shop_name")
       private String shopName;
   	   	   /* 店铺图标 */
       @TableField("shop_icon")
       private String shopIcon;
   	   	   /* appid */
       @TableField("appid")
       private String appid;
   	   	   /* appsecret */
       @TableField("appsecret")
       private String appsecret;
   	   	   /* 经度 */
       @TableField("longitude")
       private BigDecimal longitude;
   	   	   /* 纬度 */
       @TableField("latitude")
       private BigDecimal latitude;
   	   	   /* 营业时间 */
       @TableField("business_hours")
       private String businessHours;
   	   	   /* openid */
       @TableField("openid")
       private String openid;
   	   	   /* 状态 */
       @TableField("status")
       private Integer status;
   	   	   /* 配送范围 */
       @TableField("delivery_scope")
       private Integer deliveryScope;
   	   	   /* 省 */
       @TableField("province")
       private String province;
   	   	   /* 市 */
       @TableField("city")
       private String city;
   	   	   /* 县 */
       @TableField("county")
       private String county;
   	   	   /* 详细店铺地址 */
       @TableField("address")
       private String address;
   	   	   /* 排序 */
       @TableField("sort_num")
       private Integer sortNum;
   	   	   /* 创建时间 */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /* 更新时间 */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	
}
