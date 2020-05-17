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
@TableName("t_unimall_cart")
public class UnimallCart implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("sku_id")
       private Long skuId;
   	   	   /*  */
       @TableField("user_id")
       private Long userId;
   	   	   /*  */
       @TableField("num")
       private Integer num;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;

       /*关联sku商品*/
       @TableField(exist = false)
       private UnimallSku sku;

        /*关联sku商品*/
        @TableField(exist = false)
        private UnimallSpu spu;

        /*关联商户*/
        @TableField(exist = false)
        private UnimallSupplier supplier;

    /*用于查询*/
    @TableField(exist = false)
    private Long[] ids;
   	
}
