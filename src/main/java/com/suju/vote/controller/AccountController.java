package com.suju.vote.controller;

import com.suju.vote.model.Account;
import com.suju.vote.service.AccountService;
import com.suju.vote.utils.ExcelUtils;
import com.suju.vote.utils.response.RespBean;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Account)表控制层
 *
 * @author makejava
 * @since 2020-08-06 12:29:40
 */
@Api("account接口")
@RestController
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过ID查询单条数据")
    @ApiImplicitParam(name="id", value="ID", defaultValue="1", required=true)
    @GetMapping("/{id}")
    public Account selectOne(@PathVariable Integer id) {
        return this.accountService.selectById(id);
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
    public List<Account> selectAllPaging(@PathVariable int page, @PathVariable int limit) {
        int offset = (page - 1) * limit;
        return this.accountService.selectAllPaging(offset, limit);
    }
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    @ApiOperation("通过实体作为筛选条件查询")
    @ApiImplicitParam(name="", value="", defaultValue="", required=true)
    @PostMapping("/select/model")
    public List<Account> selectAll(Account account) {
        return this.accountService.selectAll(account);
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
    public List<Account> select(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("page", (page - 1) * limit);
        param.put("limit", limit);
        return this.accountService.select(param);
    }
    
    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PostMapping("/")
    public Account add(@RequestBody Account account) {
        this.accountService.add(account);
        return account;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @PutMapping("/")
    public Account update(@RequestBody Account account) {
        this.accountService.update(account);
        return this.accountService.selectById(account.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return this.accountService.deleteById(id) > 0;
    }

    /**
     * excel导入账号数据
     * */
    @PostMapping("/import")
    public RespBean importByExcel(@RequestParam("type") int type, @RequestParam("file") MultipartFile file) {
        try {
            List<Account> list = ExcelUtils.importAccount(type, file);
            for (Account account : list) {
                this.accountService.add(account);
            }
            return RespBean.ok("导入成功");
        } catch (IOException e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
    }
}