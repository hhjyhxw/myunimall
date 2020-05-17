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
@TableName("t_unimall_order_sku")
public class UnimallOrderSku implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("sku_id")
       private Long skuId;
   	   	   /*  */
       @TableField("spu_id")
       private Long spuId;
   	   	   /*  */
       @TableField("order_id")
       private Long orderId;
   	   	   /*  */
       @TableField("order_no")
       private String orderNo;
   	   	   /*  */
       @TableField("spu_title")
       private String spuTitle;
   	   	   /*  */
       @TableField("title")
       private String title;
   	   	   /*  */
       @TableField("bar_code")
       private String barCode;
   	   	   /*  */
       @TableField("num")
       private Integer num;
   	   	   /*  */
       @TableField("unit")
       private String unit;
   	   	   /*  */
       @TableField("original_price")
       private Integer originalPrice;
   	   	   /*  */
       @TableField("price")
       private Integer price;
   	   	   /*  */
       @TableField("img")
       private String img;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
