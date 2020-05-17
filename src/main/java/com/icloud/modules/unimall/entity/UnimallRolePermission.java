package com.icloud.modules.unimall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表
 * 
 * @author zdh
 * @email yyyyyy@cm.com
 * @date 2020-05-15 15:33:00
 */
@Data
@TableName("t_unimall_role_permission")
public class UnimallRolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Integer id;
   	   	   /* 角色ID */
       @TableField("role_id")
       private Integer roleId;
   	   	   /*  */
       @TableField("permission")
       private String permission;
   	   	   /* 逻辑删除 */
       @TableField("deleted")
       private Integer deleted;
   	   	   /* 更新时间 */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /* 创建时间 */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
