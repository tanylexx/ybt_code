package com.sinosoft.surrender.policyimport.batch;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.common.util.quartz.batch.MultThreadBatch;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;
import com.sinosoft.surrender.policyimport.service.SurrenderImportCoreService;

@Component("surrenderCoreBatchImpl")
public class SurrenderCoreBatchImpl implements MultThreadBatch {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SurrenderImportCoreService surrenderImportCoreService;

	@Override
	public void executeInit() throws Exception {
		logger.debug("==>批处理执行开始<==");
	}

	@Override
	public List<?> executeData() throws Exception {
		List<SurrenderImportCoreDTO> surrenderImportCoreDTOs = surrenderImportCoreService.querySurrenderData();
		if (CollectionUtils.isNotEmpty(surrenderImportCoreDTOs)) {
			return surrenderImportCoreDTOs;
		}
		return null;
	}

	@Override
	public void execute(Object obj) throws Exception {
		SurrenderImportCoreDTO surrenderImportCoreDTO = (SurrenderImportCoreDTO) obj;
		surrenderImportCoreService.doGiderCoreBatch(surrenderImportCoreDTO);
	}

	@Override
	public void executeFinal() throws Exception {
		logger.debug("==>批处理执行结束<==");
	}

}
