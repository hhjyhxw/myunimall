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
@TableName("t_unimall_freight_template")
public class UnimallFreightemplate implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("template_name")
       private String templateName;
   	   	   /*  */
       @TableField("spu_location")
       private String spuLocation;
   	   	   /*  */
       @TableField("delivery_deadline")
       private Integer deliveryDeadline;
   	   	   /*  */
       @TableField("default_free_price")
       private Integer defaultFreePrice;
   	   	   /*  */
       @TableField("default_first_num")
       private Integer defaultFirstNum;
   	   	   /*  */
       @TableField("default_first_money")
       private Integer defaultFirstMoney;
   	   	   /*  */
       @TableField("default_continue_num")
       private Integer defaultContinueNum;
   	   	   /*  */
       @TableField("default_continue_money")
       private Integer defaultContinueMoney;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	
}
