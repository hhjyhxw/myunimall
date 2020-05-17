package com.icloud.modules.unimall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:01
 */
@Data
@TableName("t_unimall_order")
public class UnimallOrder implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("channel")
       private String channel;
   	   	   /*  */
       @TableField("order_no")
       private String orderNo;
   	   	   /*  */
       @TableField("user_id")
       private Long userId;
   	   	   /*  */
       @TableField("status")
       private Integer status;
   	   	   /*  */
       @TableField("sku_original_total_price")
       private Integer skuOriginalTotalPrice;
   	   	   /*  */
       @TableField("sku_total_price")
       private Integer skuTotalPrice;
   	   	   /*  */
       @TableField("freight_price")
       private Integer freightPrice;
   	   	   /*  */
       @TableField("coupon_price")
       private Integer couponPrice;
   	   	   /*  */
       @TableField("coupon_id")
       private Long couponId;
   	   	   /*  */
       @TableField("group_shop_id")
       private Long groupShopId;
   	   	   /*  */
       @TableField("actual_price")
       private Integer actualPrice;
   	   	   /*  */
       @TableField("pay_price")
       private Integer payPrice;
   	   	   /*  */
       @TableField("pay_id")
       private String payId;
   	   	   /*  */
       @TableField("pay_channel")
       private String payChannel;
   	   	   /*  */
       @TableField("gmt_pay")
       private Date gmtPay;
   	   	   /*  */
       @TableField("ship_no")
       private String shipNo;
   	   	   /*  */
       @TableField("ship_code")
       private String shipCode;
   	   	   /*  */
       @TableField("gmt_ship")
       private Date gmtShip;
   	   	   /*  */
       @TableField("gmt_confirm")
       private Date gmtConfirm;
   	   	   /* 若province字段为空，表示无需收货地址 */
       @TableField("province")
       private String province;
   	   	   /*  */
       @TableField("city")
       private String city;
   	   	   /*  */
       @TableField("county")
       private String county;
   	   	   /*  */
       @TableField("address")
       private String address;
   	   	   /*  */
       @TableField("phone")
       private String phone;
   	   	   /*  */
       @TableField("consignee")
       private String consignee;
   	   	   /*  */
       @TableField("mono")
       private String mono;
   	   	   /*  */
       @TableField("admin_mono_level")
       private Integer adminMonoLevel;
   	   	   /*  */
       @TableField("admin_mono")
       private String adminMono;
   	   	   /*  */
       @TableField("refund_reason")
       private String refundReason;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
