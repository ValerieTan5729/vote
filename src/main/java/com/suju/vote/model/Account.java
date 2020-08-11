package com.suju.vote.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * (Account)实体类
 *
 * @author makejava
 * @since 2020-08-06 12:29:40
 */
@ApiModel
@Data
public class Account {
    /**
    * 账号ID
    */    
    @ApiModelProperty("账号ID")
    private Integer id;
    
    /**
    * 账号
    */    
    @ApiModelProperty("账号")
    private String name;
    
    /**
    * 1Melon/2Genie/3Flo/4Mubeat/5冠军秀/6小黄星
    */    
    @ApiModelProperty("1Melon/2Genie/3Flo/4Mubeat/5冠军秀/6小黄星")
    private Integer type;
    
    /**
    * 账号状态(0未使用/1使用中/2异常/3无效)
    */    
    @ApiModelProperty("账号状态(0未使用/1使用中/2异常/3无效)")
    private Integer status;
    
    /**
    * 账号状态内容
    */    
    @ApiModelProperty("账号状态内容")
    private String remark;
    
    /**
    * 修改记录
    */    
    @ApiModelProperty("修改记录")
    private String note;
    
    /**
    * 创建时间
    */    
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date creatTime;
    
    /**
    * 更新时间
    */    
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    
    /**
    * 备用字段1
    */    
    @ApiModelProperty("备用字段1")
    private String tempA;
    
    /**
    * 备用字段2
    */    
    @ApiModelProperty("备用字段2")
    private String tempB;
    


}