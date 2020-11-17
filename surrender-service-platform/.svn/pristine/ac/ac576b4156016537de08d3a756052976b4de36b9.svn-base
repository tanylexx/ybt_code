package com.sinosoft.surrender.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.guohualife.platform.common.api.util.StringUtil;
import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.PolicyApplyStateReviseResDTO;
import com.sinosoft.surrender.policyimport.service.PolicyImportDealService;

public class RevisePolicyApplyStateServlet extends HttpServlet {

	/** TODO Fields Comment add by wangwl_sinosoft */
	private static final long serialVersionUID = 1L;
	@Autowired
	private PolicyImportDealService policyImportDealService;

	public RevisePolicyApplyStateServlet() {

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 获取请求参数
		String contNo = request.getParameter("contNo");
		String acceptStatus = request.getParameter("acceptStatus");
		if(StringUtil.isNotBlank(contNo)){
			contNo = contNo.replaceAll("'", "");
		}
		if(StringUtil.isNotBlank(acceptStatus)){
			acceptStatus = acceptStatus.replaceAll("'", "");
		}
		PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO = new PolicyApplyStateReviseReqDTO();
		policyApplyStateReviseReqDTO.setAcceptStatus(acceptStatus);
		policyApplyStateReviseReqDTO.setContNo(contNo);

		// 设置响应内容
		response.setContentType("text/html;charset=utf-8");

		// 修改处理状态
		PolicyApplyStateReviseResDTO policyApplyStateReviseResDTO = policyImportDealService
				.revisePolicyApplyState(policyApplyStateReviseReqDTO);
		String resXml = XmlUtil.toXml(policyApplyStateReviseResDTO);
		out.print(resXml);
		out.flush();
		out.close();
	}

}
