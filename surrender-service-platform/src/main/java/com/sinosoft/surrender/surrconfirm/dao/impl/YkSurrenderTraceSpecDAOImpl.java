package com.sinosoft.surrender.surrconfirm.dao.impl;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YkSurrenderTrace;
import com.sinosoft.surrender.surrconfirm.dao.YkSurrenderTraceSpecDAO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

@Repository
public class YkSurrenderTraceSpecDAOImpl extends SurrenderBaseDAOImpl implements YkSurrenderTraceSpecDAO {

	@Override
	public YkSurrenderTrace findSurrenderTrace(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		Object obj = this.queryForObject("YKSURRENDERTRACESPECDAO.findSurrenderTrace", surrenderConfirmReqDTO);
		if(obj != null){
			return (YkSurrenderTrace)obj;
		}
		return null;
		
	}

}
