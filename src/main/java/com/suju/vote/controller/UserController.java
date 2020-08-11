package com.suju.vote.controller;

import com.suju.vote.model.User;
import com.suju.vote.service.UserService;
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
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-08-06 09:52:42
 */
@Api("user接口")
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过ID查询单条数据")
    @ApiImplicitParam(name="id", value="ID", defaultValue="1", required=true)
    @GetMapping("/{id}")
    public User selectOne(@PathVariable Integer id) {
        return this.userService.selectById(id);
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
    public List<User> selectAllPaging(@PathVariable int page, @PathVariable int limit) {
        int offset = (page - 1) * limit;
        return this.userService.selectAllPaging(offset, limit);
    }
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @ApiOperation("通过实体作为筛选条件查询")
    @ApiImplicitParam(name="", value="", defaultValue="", required=true)
    @PostMapping("/select/model")
    public List<User> selectAll(User user) {
        return this.userService.selectAll(user);
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
    public List<User> select(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("page", (page - 1) * limit);
        param.put("limit", limit);
        return this.userService.select(param);
    }
    
    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @PostMapping("/add")
    public User add(@RequestBody User user) {
        this.userService.add(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @PostMapping("/update")
    public User update(@RequestBody User user) {
        this.userService.update(user);
        return this.userService.selectById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Integer id) {
        return this.userService.deleteById(id) > 0;
    }

}