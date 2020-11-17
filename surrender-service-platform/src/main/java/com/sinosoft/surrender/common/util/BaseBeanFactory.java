package com.sinosoft.surrender.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalPolCashValueBO;
import com.sinosoft.surrender.common.cache.YdCashValueRuleCfgBO;
import com.sinosoft.surrender.common.constant.ENUM_EDOR_TYPE;
import com.sinosoft.surrender.db.model.YdCashValueRule;

@Component
public class BaseBeanFactory implements BeanFactoryAware {

	private BeanFactory beanFactory;
	@Autowired
	private YdCashValueRuleCfgBO ydCashValueRuleCfgBO;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	/**
	 * 	
	 * 创建映射类
	 *
	 * @history: 2018-3-15
	 * @author: wangwl_sinosoft
	 * @param riskCode
	 * @return
	 */
	public CalPolCashValueBO createCalCashValueBo(String riskCode ,String edorType) {
		Object object = null;
		// add by jinwc 2019-7-9 lisbug-6580  邮储犹退查询和犹退确认交易流程改变
		// 所有险种犹退都调用calPolCashValueCommonBOImpl计算类
		if(ENUM_EDOR_TYPE.WT.getValue().equals(edorType)) {
			object = beanFactory.getBean("calPolCashValueCommonBOImpl");
			return (CalPolCashValueBO) object;
		}
		// add end
		YdCashValueRule ydCashValueRule = ydCashValueRuleCfgBO.getYdCashValueRule(riskCode);
		ExceptionUtil.checkNull(ydCashValueRule, "此险种未配置现价计算配置,请核实!");
		String beanName =ydCashValueRule.getCalclass();
		ExceptionUtil.checkEmpty(beanName, "此险种为配置对应的计算类，请核实！");
		if (beanFactory.containsBean(beanName)) {
			object = beanFactory.getBean(beanName);
			return (CalPolCashValueBO) object;
		}
		return null;
		
	}

}
