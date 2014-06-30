package com.ailk.ts.dal.ibatis.model;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-6-10 下午06:37:10
 * 类说明:
 */

public class SelfDefineRep {
    private Long id;

    private Long skuId;

    private Long repositoryCode;

    private Integer count;
    
    /**
     * 商品名
     */
    private String skuName;
    
    /**
     * 渠道ID
     */
    private String deptId;
    
    /**
     * 仓库名
     */
    private String repName;
    
    private String typeId;
    private Integer brandId;
    private Integer productId;
    /**
     * transient 条件查询
     */
    private String condTypeId;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRepositoryCode() {
		return repositoryCode;
	}

	public void setRepositoryCode(Long repositoryCode) {
		this.repositoryCode = repositoryCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getCondTypeId() {
		return condTypeId;
	}

	public void setCondTypeId(String condTypeId) {
		this.condTypeId = condTypeId;
	}
	
}
