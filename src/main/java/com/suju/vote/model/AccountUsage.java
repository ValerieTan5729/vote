package com.suju.vote.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * (AccountUsage)实体类
 *
 * @author makejava
 * @since 2020-08-06 12:29:52
 */
@ApiModel
@Data
public class AccountUsage {
    /**
    * 使用记录ID
    */    
    @ApiModelProperty("使用记录ID")
    private Integer id;
    
    /**
    * 账号类型(1Melon/2Genie/3Flo/4Mubeat/5冠军秀/6小黄星)
    */    
    @ApiModelProperty("账号类型(1Melon/2Genie/3Flo/4Mubeat/5冠军秀/6小黄星)")
    private Integer type;
    
    /**
    * 账号ID
    */    
    @ApiModelProperty("账号ID")
    private String accountId;
    
    /**
    * 领取者ID
    */    
    @ApiModelProperty("领取者ID")
    private Integer consumerId;
    
    /**
    * 领取日期
    */    
    @ApiModelProperty("领取日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;
    
    /**
    * 使用状态(0有效/1无效)
    */    
    @ApiModelProperty("使用状态(0有效/1无效)")
    private Integer status;
    
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

    /**
     * 账号列表
     * */
    @ApiModelProperty("账号列表")
    private List<Account> accounts;
    


}