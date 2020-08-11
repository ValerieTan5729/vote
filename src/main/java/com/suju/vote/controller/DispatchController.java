package com.suju.vote.controller;

import com.suju.vote.service.AccountService;
import com.suju.vote.service.AccountUsageService;
import com.suju.vote.utils.response.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("dispatch接口")
@RestController
@RequestMapping("/dispatch")
public class DispatchController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountUsageService usageService;

    @ApiOperation("派发账号")
    @GetMapping("/{type}")
    public RespBean dispatchAccount() {
        return null;
    }
}
