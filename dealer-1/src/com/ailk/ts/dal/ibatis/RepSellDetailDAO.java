package com.ailk.ts.dal.ibatis;

import com.ailk.ts.dal.ibatis.model.RepSellDetail;
import com.ailk.ts.dal.ibatis.model.RepSellDetailExample;
import java.util.List;

public interface RepSellDetailDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int countByExample(RepSellDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int deleteByExample(RepSellDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    Long insert(RepSellDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    Long insertSelective(RepSellDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    List<RepSellDetail> selectByExample(RepSellDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    RepSellDetail selectByPrimaryKey(Long rid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int updateByExampleSelective(RepSellDetail record, RepSellDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int updateByExample(RepSellDetail record, RepSellDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RepSellDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_sell_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RepSellDetail record);
}