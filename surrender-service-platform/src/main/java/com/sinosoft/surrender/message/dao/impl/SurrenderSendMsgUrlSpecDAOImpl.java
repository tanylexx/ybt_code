package com.sinosoft.surrender.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.message.dao.SurrenderSendMsgUrlSpecDAO;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrReqlDTO;

@Repository
public class SurrenderSendMsgUrlSpecDAOImpl extends SurrenderBaseDAOImpl implements SurrenderSendMsgUrlSpecDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getSendMsgUrlOfContNoList() {
		return (List<String>)this.queryForList("SURRENDERMSGURLSPECDAO.getSendMsgUrlOfContNoList",null);
	}
	
	@Override
	public SurrenderSendMsgUrReqlDTO getSurrenderMsgUrlParam(String contno) {
		return (SurrenderSendMsgUrReqlDTO) this.queryForObject("SURRENDERMSGURLSPECDAO.getSurrenderMsgUrlParam", contno);
	}
	
	@Override
	public String queryAppntPhone(String contNo) {
		return (String) this.queryForObject("SURRENDERMSGURLSPECDAO.queryAppntPhone", contNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryMessageUrlByContNo(String contno) {
		return (List<String>)this.queryForList("SURRENDERMSGURLSPECDAO.queryMessageUrlByContNo",contno);
	}

	@Override
	public void updateStatusByContNo(YcSendMessage ycSendMessage) {
		this.updateObject("SURRENDERMSGURLSPECDAO.updateUrlStatusByContNo", ycSendMessage.getContno());

	}


}
