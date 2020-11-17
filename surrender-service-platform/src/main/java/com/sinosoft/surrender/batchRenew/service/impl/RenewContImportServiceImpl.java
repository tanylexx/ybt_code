package com.sinosoft.surrender.batchRenew.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.surrender.batchRenew.bo.RenewContImportBo;
import com.sinosoft.surrender.batchRenew.importUtils.ENUM_RENEW_STATUS;
import com.sinosoft.surrender.batchRenew.service.RenewContImportService;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.YcRenewALStatusLogDAO;
import com.sinosoft.surrender.db.model.YcRenewALStatusLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @author tianfs
 * 20200516
 * 导入核心过程
 */
@Service
public class RenewContImportServiceImpl implements RenewContImportService {
    // 连接所需的connection
    HttpURLConnection conn = null;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RenewContImportBo renewContImportBo;
    @Autowired
    YcRenewALStatusLogDAO ycRenewALstatusLogDAO;

    @Override
    public List<YcRenewALStatusLog> getRenewContDatas() {
        return renewContImportBo.getRenewContDatas();
    }

    @Override
    public void contImport(YcRenewALStatusLog ycRenewALstatusLog) {
        logger.info("开始获取保单信息");
        String tOldCno = ycRenewALstatusLog.getOldcontno();
        String tPrem = ycRenewALstatusLog.getPrem().toString();
        String tNewCno = ycRenewALstatusLog.getNewcontno();
        String prtNo = ycRenewALstatusLog.getPrtno();
        logger.info(new StringBuffer( "旧保单号：").append(tOldCno) .append("，新保单号：" ) .append(tNewCno).append("，保费：") .append(tPrem).append("保单信息获取结束").toString());
        logger.info("开始获取核心Servlet链接");
        Object renewUrl = ConfigInfo.getContextProperty("renewUrl");
        try {
            ExceptionUtil.checkNull(renewUrl, "获取的核心续保servlet地址为空！");
            String paramServletName = "?dealclass=YBTTempfee&dealmethod=dealServlet";
            String paramOldContNo = "&OldContNo=" + tOldCno;
            String paramPrem = "&Prem=" + tPrem;
            String paramNewCotnNo = "&NewContNo=" + tNewCno;
            String paramPrtNo = "&PrtNo=" + prtNo;
            StringBuffer conURL = new StringBuffer();
            conURL.append(renewUrl).append(paramServletName).append(paramOldContNo).append(paramPrem ).append(paramNewCotnNo).append(paramPrtNo);
            logger.info("导核心Servlet链接为：" + conURL);
            // 创建URL对象
            URL url = null;
            url = new URL(conURL.toString());
            // 获取连接
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // 忽略任何可用的缓存
            conn.setUseCaches(false);
            // 使用 URL连接进行输出
            conn.setDoOutput(true);
            // 使用 URL连接进行输入
            conn.setDoInput(true);
            int responseCode = conn.getResponseCode();
            logger.info("核心链接调用成功！准备导入数据");
            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream(), "GBK");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder strBuffer = new StringBuilder();
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                strBuffer.append(line);
            }
            String result = strBuffer.toString();
            JSONObject jsonObject = JSON.parseObject(result);
            String importResult = (String) jsonObject.get("result");
            if (importResult.equals("success")) {
                logger.info("更新成功！保单号：【{}】", tNewCno);
                ycRenewALstatusLog.setPolstatus(ENUM_RENEW_STATUS.IMPORT_COMPLETE.getStatus());
                ycRenewALstatusLog.setRemark("导入核心成功！");
                ycRenewALstatusLog.setModifiedDate(new Date());
                //ycRenewALstatusLog.setIsDeleted("1");
            } else {
                ycRenewALstatusLog.setRemark((String) jsonObject.get("resultDesc"));
                ycRenewALstatusLog.setModifiedDate(new Date());
                ycRenewALstatusLog.setPolstatus("F");
                logger.error("导核心过程出现异常，具体信息见ycRenewALstatusLog的Remark字段！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            ycRenewALstatusLog.setRemark("核心服务器无法连接，请查看服务器配置或联系开发人员！");
            logger.error("核心服务器无法连接，请查看服务器配置或联系开发人员！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发生未知错误{}", e.getMessage());
            String message = e.getMessage();
            if (e.getMessage().length() > 1000) {
                message = message.substring(1000);
            }
            ycRenewALstatusLog.setRemark(message);
        } finally {
            ycRenewALstatusLogDAO.updateByPrimaryKey(ycRenewALstatusLog);
        }
    }
}
