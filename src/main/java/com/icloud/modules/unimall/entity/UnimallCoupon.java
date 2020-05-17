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
@TableName("t_unimall_coupon")
public class UnimallCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 代金券名称 */
       @TableField("title")
       private String title;
   	   	   /* 使用类型，如满减 */
       @TableField("type")
       private Integer type;
   	   	   /* 描述 */
       @TableField("description")
       private String description;
   	   	   /*  */
       @TableField("total")
       private Integer total;
   	   	   /* 会员类型0:非会员1:会员2:全部 */
       @TableField("surplus")
       private Integer surplus;
   	   	   /*  */
       @TableField("limit")
       private Integer limit;
   	   	   /* 减少金额 */
       @TableField("discount")
       private Integer discount;
   	   	   /* 最低消费金额 */
       @TableField("min")
       private Integer min;
   	   	   /* 是否可用 0不用 1可用 */
       @TableField("status")
       private Integer status;
   	   	   /*  */
       @TableField("category_id")
       private Long categoryId;
   	   	   /* 过期天数 */
       @TableField("days")
       private Integer days;
   	   	   /* 领取开始时间 */
       @TableField("gmt_start")
       private Date gmtStart;
   	   	   /* 领取/使用结束时间 */
       @TableField("gmt_end")
       private Date gmtEnd;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /* 商户id */
       @TableField("supplier_id")
       private Long supplierId;
   	
}
