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
 * @date 2020-05-16 10:38:18
 */
@Data
@TableName("t_unimall_refund")
public class UnimallRefund implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /* id */
       @TableId(value="id", type= IdType.AUTO)
       private String id;
   	   	   /* 订单id */
       @TableField("order_id")
       private String orderId;
   	   	   /* 退款单号 */
       @TableField("refund_sn")
       private String refundSn;
   	   	   /* 退款金额 */
       @TableField("refund_amount")
       private String refundAmount;
   	   	   /* 退款时间 */
       @TableField("gmt_refund")
       private String gmtRefund;
   	   	   /* 创建时间 */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /* 更新时间 */
       @TableField("gmt_update")
       private String gmtUpdate;
   	
}
