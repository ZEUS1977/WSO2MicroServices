package com.wso2.microservices.chart.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductsInChartKey  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8700546511113148838L;
	
	@Column(name="CHART_ID", nullable = false)
	Integer chartId;
	
	@Column(name="PRODUCT_ID", nullable = false)
	Integer productId;
	
	public Integer getChartId() {
		return chartId;
	}
	public void setChartId(Integer chartId) {
		this.chartId = chartId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}
