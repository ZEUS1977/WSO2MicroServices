package com.wso2.microservices.chart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wso2.microservices.chart.daos.ChartDao;
import com.wso2.microservices.chart.entities.Chart;

@Service
public class ChartServiceImpl implements ChartService {
	
	@Autowired
	ChartDao chartDao;

	public Chart createNewChart(Integer accountId) {
		Chart ret = new Chart();
		ret.setAccount(accountId);
		chartDao.save(ret);
		return ret;
	}

}
