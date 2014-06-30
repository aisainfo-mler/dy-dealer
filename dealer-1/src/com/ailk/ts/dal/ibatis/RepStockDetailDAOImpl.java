package com.ailk.ts.dal.ibatis;

import com.ailk.ts.dal.ibatis.model.RepStockDetail;
import com.ailk.ts.dal.ibatis.model.RepStockDetailExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RepStockDetailDAOImpl extends SqlMapClientDaoSupport implements RepStockDetailDAO {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public RepStockDetailDAOImpl() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int countByExample(RepStockDetailExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("rep_stock_detail.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int deleteByExample(RepStockDetailExample example) {
        int rows = getSqlMapClientTemplate().delete("rep_stock_detail.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int deleteByPrimaryKey(Long detailId) {
        RepStockDetail _key = new RepStockDetail();
        _key.setDetailId(detailId);
        int rows = getSqlMapClientTemplate().delete("rep_stock_detail.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public Long insert(RepStockDetail record) {
        Object newKey = getSqlMapClientTemplate().insert("rep_stock_detail.insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public Long insertSelective(RepStockDetail record) {
        Object newKey = getSqlMapClientTemplate().insert("rep_stock_detail.insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public List<RepStockDetail> selectByExample(RepStockDetailExample example) {
        List<RepStockDetail> list = getSqlMapClientTemplate().queryForList("rep_stock_detail.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public RepStockDetail selectByPrimaryKey(Long detailId) {
        RepStockDetail _key = new RepStockDetail();
        _key.setDetailId(detailId);
        RepStockDetail record = (RepStockDetail) getSqlMapClientTemplate().queryForObject("rep_stock_detail.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int updateByExampleSelective(RepStockDetail record, RepStockDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("rep_stock_detail.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int updateByExample(RepStockDetail record, RepStockDetailExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("rep_stock_detail.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int updateByPrimaryKeySelective(RepStockDetail record) {
        int rows = getSqlMapClientTemplate().update("rep_stock_detail.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    public int updateByPrimaryKey(RepStockDetail record) {
        int rows = getSqlMapClientTemplate().update("rep_stock_detail.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rep_stock_detail
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends RepStockDetailExample {
        private Object record;

        public UpdateByExampleParms(Object record, RepStockDetailExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}