package com.sinosoft.surrender.surrconfirm.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YcSurrender;
import com.sinosoft.surrender.surrconfirm.dao.YcSurrenderSpecDAO;

@Component
public class YcSurrenderSpecDAOImpl extends SurrenderBaseDAOImpl implements YcSurrenderSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public YcSurrender findSurrenderByContNo(String contNo) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("contno", contNo);
		List<YcSurrender> list = this.queryForList("YCSURRENDERSPECDAO.findSurrenderTrace", mapParam);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;

	}

	@Override
	public void updateYcSurrenderByKey(YcSurrender ycSurrender) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("edoracceptno", ycSurrender.getEdoracceptno());
		mapParam.put("edortype", ycSurrender.getEdortype());
		this.updateObject("YCSURRENDERSPECDAO.updateYcSurrenderByKey", mapParam);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YcSurrender> findConfirmList() {
		List<YcSurrender> list = this.queryForList("YCSURRENDERSPECDAO.findConfirmList", null);
		return list;
	}

	@Override
	public BigDecimal getApplyPremByContNo(String contno) {
		Object obj = this.queryForObject("YCSURRENDERSPECDAO.getApplyPremByContNo", contno);
		if (obj != null) {
			return (BigDecimal) obj;
		}
		return null;
	}

}
