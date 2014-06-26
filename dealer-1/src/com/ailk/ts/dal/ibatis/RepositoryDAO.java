package com.ailk.ts.dal.ibatis;

import com.ailk.ts.dal.ibatis.model.Repository;
import com.ailk.ts.dal.ibatis.model.RepositoryExample;
import java.util.List;

public interface RepositoryDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int countByExample(RepositoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int deleteByExample(RepositoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String repCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    void insert(Repository record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    void insertSelective(Repository record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    List<Repository> selectByExample(RepositoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    Repository selectByPrimaryKey(String repCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int updateByExampleSelective(Repository record, RepositoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int updateByExample(Repository record, RepositoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Repository record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repository
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Repository record);
}