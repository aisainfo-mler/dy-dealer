package com.ai.mapp.sys.entity;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="HW_PRODUCT_FILTER")
public class ProductFilter implements java.io.Serializable {
	
	public static final String FILTER_TYPE_SERVICE_TYPE = "1";
	public static final String FILTER_TYPE_GEOGRAPHICLOCATION = "2";
	public static final String FILTER_TYPE_SPEED = "3";
	
	@Id
	@Column(name="FILTER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_PRODUCT_FILTER_GEN")
	@SequenceGenerator(name="HW_PRODUCT_FILTER_GEN",sequenceName="HW_PRODUCT_FILTER_SEQ")
	private Long filterId;
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Product.class)
	@JoinColumn(name="PRODUCT_ID",referencedColumnName="RANGEID")
	private Product product;
	
	@Column(name="FILTER_TYPE")
	private String filterType;
	
	@Column(name="FILTER_VALUE")
	private String filterValue;

	public Long getFilterId() {
		return filterId;
	}

	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	
}
