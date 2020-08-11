package com.suju.vote.service.impl;

import com.suju.vote.model.AccountUsage;
import com.suju.vote.mapper.AccountUsageMapper;
import com.suju.vote.service.AccountUsageService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (AccountUsage)表服务实现类
 *
 * @author makejava
 * @since 2020-08-06 12:29:52
 */
@Service("accountUsageService")
public class AccountUsageServiceImpl implements AccountUsageService {
        
    @Autowired
    private AccountUsageMapper accountUsageMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AccountUsage selectById(Integer id) {
        return this.accountUsageMapper.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AccountUsage> selectAllPaging(int offset, int limit) {
        return this.accountUsageMapper.selectAllPaging(offset, limit);
    }
    
    /**
     * 通过实体作为筛选条件查询
     *
     * @param accountUsage 实例对象
     * @return 对象列表
     */
    @Override
    public List<AccountUsage> selectAll(AccountUsage accountUsage) {
        return this.accountUsageMapper.selectAll(accountUsage);
    }
    
    /**
     * 通过Map作为筛选条件查询
     *
     * @param param 查询条件
     * @return 对象列表
     */
    @Override
    public List<AccountUsage> select(Map<String, Object> param) {
        return this.accountUsageMapper.select(param);
    }
    
    /**
     * 获取满足条件的条目数
     *
     * @param param 查询条件
     * @return int
     */
    @Override
    public Integer count(Map<String, Object> param) {
        return this.accountUsageMapper.count(param);
    }

    /**
     * 新增数据
     *
     * @param accountUsage 实例对象
     * @return 影响行数
     */
    @Override
    public int add(AccountUsage accountUsage) {
                        return this.accountUsageMapper.add(accountUsage);
    }

    /**
     * 修改数据
     *
     * @param accountUsage 实例对象
     * @return 影响行数
     */
    @Override
    public int update(AccountUsage accountUsage) {
                        return this.accountUsageMapper.update(accountUsage);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Integer id) {
        return this.accountUsageMapper.deleteById(id);
    }
}