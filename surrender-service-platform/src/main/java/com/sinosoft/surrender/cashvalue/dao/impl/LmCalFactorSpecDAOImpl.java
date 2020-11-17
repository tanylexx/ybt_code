package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sinosoft.surrender.cashvalue.dao.LmCalFactorSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LmCalFactor;

/**
 * 
 * 
 *
 * @author:  wangwl_sinosoft
 * @date: 2018-3-13-下午3:45:41
 * @version:
 */

@Repository
public class LmCalFactorSpecDAOImpl extends SurrenderBaseDAOImpl implements LmCalFactorSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LmCalFactor> getFactorByCalCode(String calCode) {
		return this.queryForList("LmCalFactorSpecDAO.getFactorByCalCode", calCode);
	}

}
