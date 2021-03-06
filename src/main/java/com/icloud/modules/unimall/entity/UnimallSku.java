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
@TableName("t_unimall_sku")
public class UnimallSku implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 商品spuid */
       @TableField("spu_id")
       private Long spuId;
   	   	   /* sku条码 */
       @TableField("bar_code")
       private String barCode;
   	   	   /* sku名称 */
       @TableField("title")
       private String title;
   	   	   /* 图片 */
       @TableField("img")
       private String img;
   	   	   /* 原始价 */
       @TableField("original_price")
       private Integer originalPrice;
   	   	   /* 现价 */
       @TableField("price")
       private Integer price;
   	   	   /* vip价 */
       @TableField("vip_price")
       private Integer vipPrice;
   	   	   /* 库存 */
       @TableField("stock")
       private Integer stock;
   	   	   /* 冻结库存 */
       @TableField("freeze_stock")
       private Integer freezeStock;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;

        /*关联spu*/
        @TableField(exist = false)
        private UnimallSpu spu;
   	
}
