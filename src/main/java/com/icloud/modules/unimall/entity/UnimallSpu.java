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
@TableName("t_unimall_spu")
public class UnimallSpu implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 原价 */
       @TableField("original_price")
       private Integer originalPrice;
   	   	   /* 现价 */
       @TableField("price")
       private Integer price;
   	   	   /* vip价 */
       @TableField("vip_price")
       private Integer vipPrice;
   	   	   /* 商品名称 */
       @TableField("title")
       private String title;
   	   	   /* 销量 */
       @TableField("sales")
       private Integer sales;
   	   	   /* 商品图片 */
       @TableField("img")
       private String img;
   	   	   /* 商品详情 */
       @TableField("detail")
       private String detail;
   	   	   /* 商品描述 */
       @TableField("description")
       private String description;
   	   	   /* 分类id */
       @TableField("category_id")
       private Long categoryId;
   	   	   /* 运费模板id */
       @TableField("freight_template_id")
       private Long freightTemplateId;
   	   	   /* 计量单位 */
       @TableField("unit")
       private String unit;
   	   	   /* 0下架 1上架 */
       @TableField("status")
       private Integer status;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /* 商户id */
       @TableField("supplier_id")
       private Long supplierId;

    /*关联supplier*/
    @TableField(exist = false)
    private UnimallSupplier supplier;
   	
}
