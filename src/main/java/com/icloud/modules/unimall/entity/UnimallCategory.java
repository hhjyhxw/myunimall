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
@TableName("t_unimall_category")
public class UnimallCategory implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("title")
       private String title;
   	   	   /*  */
       @TableField("parent_id")
       private Long parentId;
   	   	   /*  */
       @TableField("icon_url")
       private String iconUrl;
   	   	   /*  */
       @TableField("pic_url")
       private String picUrl;
   	   	   /*  */
       @TableField("level")
       private Integer level;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
