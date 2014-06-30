package com.ailk.ts.dal.ibatis;

import com.ailk.ts.dal.ibatis.model.RepStockDetail;
import com.ailk.ts.dal.ibatis.model.RepStockDetailExample;
import java.util.List;

public interface RepStockDetailDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int countByExample(RepStockDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int deleteByExample(RepStockDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long detailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    Long insert(RepStockDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    Long insertSelective(RepStockDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    List<RepStockDetail> selectByExample(RepStockDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    RepStockDetail selectByPrimaryKey(Long detailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int updateByExampleSelective(RepStockDetail record, RepStockDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int updateByExample(RepStockDetail record, RepStockDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RepStockDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RepStockDetail record);
}