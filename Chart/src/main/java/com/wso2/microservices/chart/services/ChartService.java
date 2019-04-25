package com.wso2.microservices.chart.services;

import com.wso2.microservices.chart.entities.Chart;

public interface ChartService{
	
	public Chart createNewChart(Integer accountId);
}
