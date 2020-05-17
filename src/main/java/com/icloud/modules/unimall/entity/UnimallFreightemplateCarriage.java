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
@TableName("t_unimall_freight_template_carriage")
public class UnimallFreightemplateCarriage implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("template_id")
       private Long templateId;
   	   	   /*  */
       @TableField("designated_area")
       private String designatedArea;
   	   	   /*  */
       @TableField("free_price")
       private Integer freePrice;
   	   	   /*  */
       @TableField("first_num")
       private Integer firstNum;
   	   	   /*  */
       @TableField("first_money")
       private Integer firstMoney;
   	   	   /*  */
       @TableField("continue_num")
       private Integer continueNum;
   	   	   /*  */
       @TableField("continue_money")
       private Integer continueMoney;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	
}
