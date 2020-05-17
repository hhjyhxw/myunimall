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
@TableName("t_unimall_user")
public class UnimallUser implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /* 电话 */
       @TableField("phone")
       private String phone;
   	   	   /* 密码 */
       @TableField("password")
       private String password;
   	   	   /* 登陆类型 */
       @TableField("login_type")
       private Integer loginType;
   	   	   /* 登陆名称 */
       @TableField("loginname")
       private String loginname;
   	   	   /* 邮箱 */
       @TableField("email")
       private String email;
   	   	   /*  */
       @TableField("open_id")
       private String openId;
   	   	   /* 昵称 */
       @TableField("nickname")
       private String nickname;
   	   	   /* 头像url */
       @TableField("avatar_url")
       private String avatarUrl;
   	   	   /* 0.普通会员 1.VIP会员 */
       @TableField("level")
       private Integer level;
   	   	   /* 生日 */
       @TableField("birthday")
       private Date birthday;
   	   	   /* 性别 */
       @TableField("gender")
       private Integer gender;
   	   	   /* 最近登陆时间 */
       @TableField("gmt_last_login")
       private Date gmtLastLogin;
   	   	   /* 最近登陆ip */
       @TableField("last_login_ip")
       private String lastLoginIp;
   	   	   /* 1 正常 2禁用 3注销  */
       @TableField("status")
       private Integer status;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	
}
