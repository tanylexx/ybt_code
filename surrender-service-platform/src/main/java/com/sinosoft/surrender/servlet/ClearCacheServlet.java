package com.sinosoft.surrender.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sinosoft.surrender.cashvalue.dao.YcPolicyCashValueSpecDAO;
import com.sinosoft.surrender.common.cache.RedisCacheBO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.YcPolicyCashValueDAO;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;
import com.sinosoft.surrender.db.model.YcPolicyCashValueExample;

public class ClearCacheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
    private YcPolicyCashValueSpecDAO ycPolicyCashValueSpecDAO;
	@Autowired
    private YcPolicyCashValueDAO ycPolicyCashValueDAO;
	@Autowired
	private RedisCacheBO redisCacheBO;
	private Logger logger = LoggerFactory.getLogger(getClass());
    public ClearCacheServlet() {
        super();
    }
    @Override
   	public void init(ServletConfig config) throws ServletException {
   		 SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext()); 
   	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			logger.info("=====================================================");
			//从现价表中获取所有数据
			logger.info("==============获取所有数据================");
			//List<YcPolicyCashValue> cashValueList = ycPolicyCashValueSpecDAO.getAllYcPolicyCashValue();
			
			YcPolicyCashValueExample example = new YcPolicyCashValueExample();
			example.createCriteria().andIsDeletedEqualTo("0");
			List<YcPolicyCashValue> cashValueList = ycPolicyCashValueDAO.selectByExample(example );
			if(CollectionUtils.isEmpty(cashValueList)){
				return;
			}
			//redis刪除缓存数据
			logger.info("==============刪除缓存====================");
			for(YcPolicyCashValue ycPolicyCashValue : cashValueList){
				logger.info("==============循环====================");
				StringBuffer sbf = new StringBuffer();
				sbf.append(ycPolicyCashValue.getEdortype());
				sbf.append("|");
				sbf.append(ycPolicyCashValue.getContno());
				sbf.append("|");
				sbf.append(DateUtil.getDateStr(ycPolicyCashValue.getApplyDate(), "yyyy-MM-dd"));
				String key = sbf.toString();
				logger.info("==============刪除数据================");
				redisCacheBO.deleteRedisCache(key);
			}
			//删除现价表中的所有数据
			logger.info("==============刪除所有数据================");
			ycPolicyCashValueSpecDAO.updateAllYcPolicyCashValue();
			logger.info("=====================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
