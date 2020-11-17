package com.sinosoft.surrender.cashvalue.dao;

import java.util.Date;
import java.util.List;

import com.sinosoft.surrender.db.model.LcGet;

public interface LcGetSpecDAO {
	/**
	 * 
	 * 根据 polNo,保单年度开始时间以及结束时间查询lcget
	 * 
	 * @history: 2018-4-2
	 * @author: wangwl_sinosoft
	 * @param polNo
	 * @param manageCom
	 * @param dateSatrt
	 * @param dateEnd
	 * @return
	 */
	List<LcGet> getLcGetInfos(String polNo, Date dateSatrt, Date dateEnd);
}
