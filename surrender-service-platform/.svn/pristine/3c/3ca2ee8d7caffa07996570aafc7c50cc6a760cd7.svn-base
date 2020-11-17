package com.sinosoft.surrender.policyimport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.surrender.policyimport.bo.QuerySurrenderDataBO;
import com.sinosoft.surrender.policyimport.bo.SurrenderImportCoreBO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;
import com.sinosoft.surrender.policyimport.service.SurrenderImportCoreService;

@Service
public class SurrenderImportCoreServiceImpl implements SurrenderImportCoreService {
	@Autowired
	private SurrenderImportCoreBO surrenderImportCoreBO;
	@Autowired
	private QuerySurrenderDataBO querySurrenderDataBO;

	@Override
	public void doGiderCoreBatch(SurrenderImportCoreDTO surrenderImportCoreDTO) {
		surrenderImportCoreBO.doGiderCoreBatch(surrenderImportCoreDTO);
	}

	@Override
	public List<SurrenderImportCoreDTO> querySurrenderData() {
		List<SurrenderImportCoreDTO> querySurrenderData = querySurrenderDataBO.querySurrenderData();
		return querySurrenderData;
	}

}
