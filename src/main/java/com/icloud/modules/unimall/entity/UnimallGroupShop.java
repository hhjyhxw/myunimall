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
@TableName("t_unimall_group_shop")
public class UnimallGroupShop implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("spu_id")
       private Long spuId;
   	   	   /*  */
       @TableField("min_price")
       private Integer minPrice;
   	   	   /*  */
       @TableField("max_price")
       private Integer maxPrice;
   	   	   /* 团购开始时间 */
       @TableField("gmt_start")
       private Date gmtStart;
   	   	   /* 团购结束时间 */
       @TableField("gmt_end")
       private Date gmtEnd;
   	   	   /* 团购基础人数 */
       @TableField("minimum_number")
       private Integer minimumNumber;
   	   	   /* 团购已经购买人数 */
       @TableField("already_buy_number")
       private Integer alreadyBuyNumber;
   	   	   /* 团购结束时购买人数未达到基础人数,是否自动退款 */
       @TableField("automatic_refund")
       private Integer automaticRefund;
   	   	   /* 判断团购商品是否在活动期间 */
       @TableField("status")
       private Integer status;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	
}
