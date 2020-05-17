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
@TableName("t_unimall_collect")
public class UnimallCollect implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("user_id")
       private Long userId;
   	   	   /*  */
       @TableField("spu_id")
       private Long spuId;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	
}
