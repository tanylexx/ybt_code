package com.sinosoft.surrender.cashvalue.batch;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.common.util.quartz.batch.MultThreadBatch;
import com.sinosoft.surrender.cashvalue.bo.YcPolicyCashValueBO;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.YcPolicyCashValueDAO;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;
/**
 * 清理本地缓存批处理
 * @author zhongdw_sinosoft
 *
 */
@Component("cleanLocalCacheBatchImpl")
public class CleanLocalCacheBatchImpl implements MultThreadBatch{
	@Autowired
	private YcPolicyCashValueBO ycPolicyCashValueBO;
	
	@Autowired
	private YcPolicyCashValueDAO ycPolicyCashValueDAO;
	
	@Override
	public void executeInit() throws Exception {
		
	}

	@Override
	public List<?> executeData() throws Exception {
		// 从配置文件中获取天数
		Object obj = ConfigInfo.getContextProperty("DELETEDAYS");
		int deleteDays = Integer.valueOf((String) obj); 
		// 获取几天前的日期
		Date calDate = DateUtil.calDate(new Date(), -deleteDays);
		// 查询几天前的数据
		return ycPolicyCashValueBO.getDaysAgoYcPolicyCashValue(calDate);
	}

	@Override
	public void execute(Object obj) throws Exception {
		if(obj != null){
			YcPolicyCashValue ycPolicyCashValue = (YcPolicyCashValue)obj;
			// 根据主键删除
			ycPolicyCashValueDAO.deleteByPrimaryKey(ycPolicyCashValue.getSid());
		}
	}

	@Override
	public void executeFinal() throws Exception {
		
	}

}
