package com.sinosoft.surrender.surrconfirm.dao.impl;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.surrconfirm.dao.LdCodeSpecDAO;

@Repository
public class LdCodeSpecDAOImpl extends SurrenderBaseDAOImpl implements LdCodeSpecDAO {

	@Override
	public String findCodeName(String codeType) {
		Object obj = this.queryForObject("LDCODESPECDAO.findCodeName", codeType);
		if (obj != null) {
			return (String) obj;
		}

		return null;
	}

}
