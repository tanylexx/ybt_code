package com.sinosoft.surrender.servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;
import com.sinosoft.surrender.policyimport.service.PolicyImportDealService;

public class QuerySurrenderServlet extends HttpServlet {
	
	/** TODO Fields Comment add by wangwl_sinosoft */
	private static final long serialVersionUID = 1L;
	@Autowired
	private PolicyImportDealService policyImportDealService;
	
	public QuerySurrenderServlet(){
		 super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		 SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext()); 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// 获取请求参数
		StringBuffer resContent = new StringBuffer();
		DataInputStream dataIn = new DataInputStream(request.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(dataIn, "UTF-8"));
		String aLine;
		while ((aLine = reader.readLine()) != null) {
			resContent.append(aLine);
		}
		String param = java.net.URLDecoder.decode(resContent.toString(), "UTF-8");;
		dataIn.close();
		reader.close();

		//将xml转成Object
		QuerySurrenderReqDTO querySurrenderReqDTO = (QuerySurrenderReqDTO) XmlUtil.fromXml(param, new Class[]{QuerySurrenderReqDTO.class});
		//查询数据
		List<QuerySurrenderResDTO> surrenderResDTOlist = policyImportDealService.querySurrender(querySurrenderReqDTO);
		//list转成json格式返回
		//JSONArray array = (JSONArray) JSONArray.toJSON(surrenderResDTOlist);
		
		//list转成xml格式返回
		String resList = XmlUtil.toXml(surrenderResDTOlist);
		//创建流
		PrintWriter out = response.getWriter();
		// 设置响应内容
		response.setContentType("text/xml;charset=utf-8");
		out.print(resList);
		out.flush();
		out.close();
	}

}
