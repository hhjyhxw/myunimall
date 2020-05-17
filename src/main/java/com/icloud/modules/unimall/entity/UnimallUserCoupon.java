package com.icloud.modules.unimall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户优惠卷表
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Data
@TableName("t_unimall_user_coupon")
public class UnimallUserCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 用户id */
       @TableField("user_id")
       private Long userId;
   	   	   /* 代金券id */
       @TableField("coupon_id")
       private Long couponId;
   	   	   /* 使用订单Id */
       @TableField("order_id")
       private Long orderId;
   	   	   /* 使用时间，若使用时间为空，表示未使用 */
       @TableField("gmt_used")
       private Date gmtUsed;
   	   	   /* 领取优惠券时写入 */
       @TableField("gmt_start")
       private Date gmtStart;
   	   	   /* 领取优惠券过期时间根据策略计算 */
       @TableField("gmt_end")
       private Date gmtEnd;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
