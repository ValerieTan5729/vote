package com.suju.vote.controller;

import com.suju.vote.model.AccountUsage;
import com.suju.vote.service.AccountUsageService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (AccountUsage)表控制层
 *
 * @author makejava
 * @since 2020-08-06 12:29:52
 */
@Api("accountUsage接口")
@RestController
@RequestMapping("/accountUsage")
public class AccountUsageController {
    
    @Autowired
    private AccountUsageService accountUsageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过ID查询单条数据")
    @ApiImplicitParam(name="id", value="ID", defaultValue="1", required=true)
    @GetMapping("/{id}")
    public AccountUsage selectOne(@PathVariable Integer id) {
        return this.accountUsageService.selectById(id);
    }
    
    /**
     * 分页查询
     *
     * @param page 查询起始页数
     * @param limit 查询条数
     * @return 对象列表
     */
    @ApiOperation("分页查询数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name="page", value="页码", defaultValue="1", required=true),
        @ApiImplicitParam(name="limit", value="每页展示的条数", defaultValue="10", required=true)
    })
    @GetMapping("/page/{page}/{limit}")
    public List<AccountUsage> selectAllPaging(@PathVariable int page, @PathVariable int limit) {
        int offset = (page - 1) * limit;
        return this.accountUsageService.selectAllPaging(offset, limit);
    }
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param accountUsage 实例对象
     * @return 对象列表
     */
    @ApiOperation("通过实体作为筛选条件查询")
    @ApiImplicitParam(name="", value="", defaultValue="", required=true)
    @PostMapping("/select/model")
    public List<AccountUsage> selectAll(AccountUsage accountUsage) {
        return this.accountUsageService.selectAll(accountUsage);
    }
    
    /**
     * 通过Map作为筛选条件查询
     *
     * @param page 页数
     * @param limit 条数
     * @return 对象列表
     */
    @ApiOperation("条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value="页码", defaultValue="1", required=true),
            @ApiImplicitParam(name="limit", value="每页展示的条数", defaultValue="10", required=true)
    })
    @GetMapping("/")
    public List<AccountUsage> select(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("page", (page - 1) * limit);
        param.put("limit", limit);
        return this.accountUsageService.select(param);
    }
    
    /**
     * 新增数据
     *
     * @param accountUsage 实例对象
     * @return 实例对象
     */
    @PostMapping("/add")
    public AccountUsage add(@RequestBody AccountUsage accountUsage) {
        this.accountUsageService.add(accountUsage);
        return accountUsage;
    }

    /**
     * 修改数据
     *
     * @param accountUsage 实例对象
     * @return 实例对象
     */
    @PostMapping("/update")
    public AccountUsage update(@RequestBody AccountUsage accountUsage) {
        this.accountUsageService.update(accountUsage);
        return this.accountUsageService.selectById(accountUsage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return this.accountUsageService.deleteById(id) > 0;
    }

}