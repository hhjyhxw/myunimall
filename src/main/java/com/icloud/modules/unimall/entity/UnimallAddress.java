package com.icloud.modules.unimall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_unimall_address")
public class UnimallAddress implements Serializable {
	private static final long serialVersionUID = 1L;

   	   /*  */
       @TableId(value="id", type= IdType.AUTO)
       private Long id;
   	   	   /*  */
       @TableField("province")
       private String province;
   	   	   /*  */
       @TableField("city")
       private String city;
   	   	   /*  */
       @TableField("county")
       private String county;
   	   	   /*  */
       @TableField("address")
       private String address;
   	   	   /*  */
       @TableField("default_address")
       private Integer defaultAddress;
   	   	   /*  */
       @TableField("user_id")
       private Long userId;
   	   	   /*  */
       @TableField("phone")
       private String phone;
   	   	   /* 收件人 */
       @TableField("consignee")
       private String consignee;
   	   	   /*  */
       @TableField("gmt_create")
       private Date gmtCreate;
   	   	   /*  */
       @TableField("gmt_update")
       private Date gmtUpdate;

    public UnimallAddress(String province, String city, String county, String address, int defaultAddress, Long userId, String phone, String consignee) {
        this.province = province;
        this.city = city;
        this.county = county;
        this.address = address;
        this.defaultAddress = defaultAddress;
        this.userId = userId;
        this.phone = phone;
        this.consignee = consignee;
    }


}
