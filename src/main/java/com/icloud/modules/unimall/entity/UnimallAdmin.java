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
 * @date 2020-05-15 15:33:02
 */
@Data
@TableName("t_unimall_admin")
public class UnimallAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("username")
       private String username;
   	   	   /*  */
       @TableField("password")
       private String password;
   	   	   /*  */
       @TableField("phone")
       private String phone;
   	   	   /*  */
       @TableField("realname")
       private String realname;
   	   	   /*  */
       @TableField("avatar_url")
       private String avatarUrl;
   	   	   /*  */
       @TableField("role_ids")
       private String roleIds;
   	   	   /* 0.冻结 1.激活 */
       @TableField("status")
       private Integer status;
   	   	   /*  */
       @TableField("last_login_ip")
       private String lastLoginIp;
   	   	   /*  */
       @TableField("gmt_last_login")
       private Date gmtLastLogin;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
