package com.icloud.modules.unimall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付流水表 支付流水表
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-16 10:38:18
 */
@Data
@TableName("t_unimall_payment")
public class UnimallPayment implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /* id */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 支付单号 */
       @TableField("pay_sn")
       private String paySn;
   	   	   /* 支付状态 0 未支付，1支付中 2已支付 3已退款 4支付失败 */
       @TableField("status")
       private Integer status;
   	   	   /* 支付方式 0微信 */
       @TableField("pay_way")
       private Integer payWay;
   	   	   /* 支付金额 以分为单位 */
       @TableField("pay_amount")
       private Integer payAmount;
   	   	   /* 支付手续费 */
       @TableField("pay_fee")
       private Integer payFee;
   	   	   /* 用户id */
       @TableField("user_id")
       private Long userId;
   	   	   /* openid */
       @TableField("openid")
       private String openid;
   	   	   /* 交易单号 */
       @TableField("transaction_id")
       private String transactionId;
   	   	   /* 支付时间 */
       @TableField("gmt_pay")
       private Date gmtPay;
   	   	   /* 创建时间 */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /* 修改时间 */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	
}
