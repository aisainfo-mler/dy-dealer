package com.ailk.ts.dal.ibatis;

import com.ailk.ts.dal.ibatis.model.RepOptRecord;
import com.ailk.ts.dal.ibatis.model.RepOptRecordExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RepOptRecordDAOImpl extends SqlMapClientDaoSupport implements RepOptRecordDAO {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public RepOptRecordDAOImpl() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int countByExample(RepOptRecordExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("rep_opt_record.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int deleteByExample(RepOptRecordExample example) {
        int rows = getSqlMapClientTemplate().delete("rep_opt_record.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int deleteByPrimaryKey(Long serialNo) {
        RepOptRecord _key = new RepOptRecord();
        _key.setSerialNo(serialNo);
        int rows = getSqlMapClientTemplate().delete("rep_opt_record.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public Long insert(RepOptRecord record) {
        Object newKey = getSqlMapClientTemplate().insert("rep_opt_record.insert", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public Long insertSelective(RepOptRecord record) {
        Object newKey = getSqlMapClientTemplate().insert("rep_opt_record.insertSelective", record);
        return (Long) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    @SuppressWarnings("unchecked")
    public List<RepOptRecord> selectByExample(RepOptRecordExample example) {
        List<RepOptRecord> list = getSqlMapClientTemplate().queryForList("rep_opt_record.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public RepOptRecord selectByPrimaryKey(Long serialNo) {
        RepOptRecord _key = new RepOptRecord();
        _key.setSerialNo(serialNo);
        RepOptRecord record = (RepOptRecord) getSqlMapClientTemplate().queryForObject("rep_opt_record.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int updateByExampleSelective(RepOptRecord record, RepOptRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("rep_opt_record.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int updateByExample(RepOptRecord record, RepOptRecordExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("rep_opt_record.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int updateByPrimaryKeySelective(RepOptRecord record) {
        int rows = getSqlMapClientTemplate().update("rep_opt_record.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    public int updateByPrimaryKey(RepOptRecord record) {
        int rows = getSqlMapClientTemplate().update("rep_opt_record.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table rep_opt_record
     *
     * @mbggenerated
     */
    protected static class UpdateByExampleParms extends RepOptRecordExample {
        private Object record;

        public UpdateByExampleParms(Object record, RepOptRecordExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}