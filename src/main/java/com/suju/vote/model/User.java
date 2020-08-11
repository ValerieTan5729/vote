package com.suju.vote.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-08-06 09:52:39
 */
@ApiModel
@Data
public class User implements UserDetails {
    /**
    * 用户ID
    */    
    @ApiModelProperty("用户ID")
    private Integer id;
    
    /**
    * 用户名
    */    
    @ApiModelProperty("用户名")
    private String name;
    
    /**
    * 用户密码
    */    
    @ApiModelProperty("用户密码")
    private String password;
    
    /**
    * 用户状态(0有效/1无效)
    */    
    @ApiModelProperty("用户状态(0有效/1无效)")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(2);
        authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        authorities.add(new SimpleGrantedAuthority("ROLE_test"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status==0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status==0;
    }

    @Override
    public boolean isEnabled() {
        return status==0;
    }
}