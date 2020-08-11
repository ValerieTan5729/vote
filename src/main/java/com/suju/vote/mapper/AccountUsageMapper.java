package com.suju.vote.mapper;

import com.suju.vote.model.AccountUsage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * (AccountUsage)表数据库访问层mapper
 *
 * @author makejava
 * @since 2020-08-06 12:29:52
 */
@Component
public interface AccountUsageMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AccountUsage selectById(Integer id);

    /**
     * 分页查询
     *
     * @param offset 查询起始偏移值
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AccountUsage> selectAllPaging(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param accountUsage 实例对象
     * @return 对象列表
     */
    List<AccountUsage> selectAll(AccountUsage accountUsage);
    
    /**
     * 通过Map作为筛选条件查询
     *
     * @param param 查询条件
     * @return 对象列表
     */
    List<AccountUsage> select(Map<String, Object> param);
    
    /**
     * 获取满足条件的条目数
     *
     * @param param 查询条件
     * @return int
     */
    Integer count(Map<String, Object> param);

    /**
     * 新增数据
     *
     * @param accountUsage 实例对象
     * @return 影响行数
     */
    int add(AccountUsage accountUsage);

    /**
     * 修改数据
     *
     * @param accountUsage 实例对象
     * @return 影响行数
     */
    int update(AccountUsage accountUsage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}