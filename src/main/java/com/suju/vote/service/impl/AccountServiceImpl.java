package com.suju.vote.service.impl;

import com.suju.vote.model.Account;
import com.suju.vote.mapper.AccountMapper;
import com.suju.vote.service.AccountService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2020-08-06 12:29:40
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
        
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Account selectById(Integer id) {
        return this.accountMapper.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Account> selectAllPaging(int offset, int limit) {
        return this.accountMapper.selectAllPaging(offset, limit);
    }
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @return 对象列表
     */
    @Override
    public List<Account> selectAll(Account account) {
        return this.accountMapper.selectAll(account);
    }
    
    /**
     * 通过Map作为筛选条件查询
     *
     * @param param 查询条件
     * @return 对象列表
     */
    @Override
    public List<Account> select(Map<String, Object> param) {
        return this.accountMapper.select(param);
    }
    
    /**
     * 获取满足条件的条目数
     *
     * @param param 查询条件
     * @return int
     */
    @Override
    public Integer count(Map<String, Object> param) {
        return this.accountMapper.count(param);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    @Override
    public int add(Account account) {
                        return this.accountMapper.add(account);
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 影响行数
     */
    @Override
    public int update(Account account) {
                        return this.accountMapper.update(account);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.accountMapper.deleteById(id);
    }
}