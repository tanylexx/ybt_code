package com.sinosoft.surrender.cashvalue.batch;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sinosoft.common.util.quartz.batch.MultThreadBatch;
import com.sinosoft.surrender.cashvalue.bo.QueryPrecalculatedParamBO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.service.CalPolicyCashValueService;
import com.sinosoft.surrender.common.constant.ENUM_EDOR_TYPE;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.DateUtil;
/**
 * 调核心试算批处理
 * @author zhongdw_sinosoft
 *
 */
@Component("callCoreCalCashValueBatchImpl")
public class CallCoreCalCashValueBatchImpl implements MultThreadBatch{
	@Autowired
	private QueryPrecalculatedParamBO queryPolicyCashValueReqDTOBO;
	@Autowired
	private CalPolicyCashValueService calPolicyCashValueService;
	@Override
	public void executeInit() throws Exception {
		
	}

	@Override
	public List<?> executeData() throws Exception {
		return queryPolicyCashValueReqDTOBO.queryCallCorePrecalculatedParam();
	}

	@Override
	public void execute(Object obj) throws Exception {
		if(obj != null){
			PolicyCashValueReqDTO policyCashValueReqDTO = (PolicyCashValueReqDTO)obj;
			int  flag = 0;
			Object objStr = ConfigInfo.getContextProperty("SPCEFLAG");
			if (obj != null) {
				flag = Integer.valueOf((String) objStr);
			}
			StringBuffer sbf =new StringBuffer();
			sbf.append("YJS");
			sbf.append(DateUtil.getDateStr(new Date(), "yyyyMMddhhmmss"));
			policyCashValueReqDTO.setTransNo(sbf.toString());
			policyCashValueReqDTO.setTellerNo("YJSBATCH");
			policyCashValueReqDTO.setEdorType(ENUM_EDOR_TYPE.CT.getValue());
			policyCashValueReqDTO.setFunctionFlag("26");
			policyCashValueReqDTO.setApplyDate(DateUtil.calDate(DateUtil.getCurrentDate(), flag, DateUtil.DATE_TYPE_D, null));
			policyCashValueReqDTO.setApplyTime(DateUtil.getCurrentTime());
			policyCashValueReqDTO.setApalyPlatform("YJSBATCH");
			calPolicyCashValueService.calPolicyCashValue(policyCashValueReqDTO);
		}
	}

	@Override
	public void executeFinal() throws Exception {
	
	}

}
