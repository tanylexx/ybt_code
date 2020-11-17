package com.sinosoft.surrender.addprem.bo.impl;

import com.sinosoft.surrender.addprem.bo.AddPremCheckBO;
import com.sinosoft.surrender.addprem.dao.AddPremDao;
import com.sinosoft.surrender.addprem.dto.AddPremUWRulesDto;
import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.common.config.bo.ConfigInfoBO;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.model.LcPol;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class AddPremCheckBOImpl implements AddPremCheckBO {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConfigInfoBO configInfoBO;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private AddPremDao addPremDao;

    @Override
    public void checkParam(PEdorAddPremReqDto pEdorAddPremReqDto) {
        // 请求参数不为空
        ExceptionUtil.checkNull(pEdorAddPremReqDto, "请求参数不能为空！");
        // 参数必传属性不为空
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getTransNo(), "退保确认流水号不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getContNo(), "保单号不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getZoneNo(), "地区码不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getBrNo(), "网点码不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getBankCode(), "银行编码不能为空！");
        ExceptionUtil.checkNull(pEdorAddPremReqDto.getApplyDate(), "确认日期不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getAppType(), "申请平台不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getBankAccNo(), "银行账号不能为空！");
        ExceptionUtil.checkEmpty(pEdorAddPremReqDto.getAccName(), "银行账户名不能为空！");
    }

    @Override
    public void checkUWRules(PEdorAddPremReqDto pEdorAddPremReqDto, AlgorithElementDTO algorithElementDTO, LcPol lcPol) throws IllegalAccessException {
        // 计算风险保额
        calRiskAmnt(algorithElementDTO, lcPol);
        //从配置中心获取该险种的所有核保规则
        List<AddPremUWRulesDto> addPremUWRulesDtoList = configInfoBO.getUWRulesByRiskCode(lcPol.getRiskcode());
        //循环每一条核保规则
        if(CollectionUtils.isNotEmpty(addPremUWRulesDtoList)){
            for(AddPremUWRulesDto addPremUWRulesDto : addPremUWRulesDtoList){
                String uwCheckSql = addPremUWRulesDto.getSql();
                //执行sql语句
                String exeResult = exeWithParam(uwCheckSql, algorithElementDTO);
                //如果有核保规则的sql执行结果为1，则核保规则不通过
                if("1".equals(exeResult)){
                    throw new RulesCheckedException("核保错误："+ addPremUWRulesDto.getRemark());
                }
            }

        }
    }

    /**
     * 计算风险保额
     * @param algorithElementDTO
     * @param lcPol
     */
    private void calRiskAmnt(AlgorithElementDTO algorithElementDTO, LcPol lcPol) {
        //先取出该险种累计的风险保额类型
        String riskAmntTypeStr = configInfoBO.getRiskAmntTypeByRiskCode(lcPol.getRiskcode());
        List<String> riskAmntTypeList = null;
        if(StringUtils.isEmpty(riskAmntTypeStr)){
            return;
        }
        riskAmntTypeList = Arrays.asList(riskAmntTypeStr.split(","));

        //然后调用风险保额函数
        for(String type: riskAmntTypeList ){
            //先算出风险保额
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("insuredno", lcPol.getInsuredno());
            map.put("contno", lcPol.getContno());
            map.put("type", type);
            map.put("newInsuredFlag", 0); //追加保费默认是老客户
            String riskamnt = addPremDao.calRiskAmnt(map);
            //寿险风险保额
            if("K".equals(type)){
                algorithElementDTO.setMaxamnt(new BigDecimal(riskamnt));
            }
            //重疾险风险保额
            if("I".equals(type)){
                algorithElementDTO.setMaxamnt(new BigDecimal(riskamnt));
            }
            //累计意外保额
            if("J".equals(type)){
                algorithElementDTO.setMaxamnt(new BigDecimal(riskamnt));
            }
            //累计驾乘意外保额
            if("D".equals(type)){
                algorithElementDTO.setMaxamnt(new BigDecimal(riskamnt));
            }
            //累计航空意外风险保额
            if("H".equals(type)){
                algorithElementDTO.setMaxamnt(new BigDecimal(riskamnt));
            }
        }
    }

    /**
     *
     * 解析sql
     *
     * @history: 2018-3-15
     * @author: wangwl_sinosoft
     * @param sSql
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    private String exeWithParam(String sSql, Object obj) throws IllegalArgumentException,
            IllegalAccessException {
        if (obj != null) {
            Map<String, Object> objMap;
            if (obj instanceof Map) {
                objMap = new CaseInsensitiveMap((Map<String, Object>) obj);

            } else {
                objMap = new CaseInsensitiveMap();
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (!field.getType().isPrimitive()) {
                        if (field.get(obj) == null && field.getType().getSuperclass() == Number.class) {
                            objMap.put(field.getName(), BigDecimal.ZERO);
                            continue;
                        }
                    }
                    objMap.put(field.getName(), field.get(obj));
                }
            }

            int fInd = sSql.indexOf('?');
            while (fInd >= 0 && sSql.length() > (fInd + 1)) {
                String sSubSql = sSql.substring(fInd + 1);

                int sInd = sSubSql.indexOf('?');
                if (sInd >= 0) {

                    sSql = sSql.replace(sSql.substring(fInd, fInd + 2 + sInd),
                            getParamStr(objMap, sSql.substring(fInd + 1, fInd + 1 + sInd)));
                } else {
                    break;
                }
                fInd = sSql.indexOf('?');
            }

        }
        logger.info("sSql==" + sSql);
        String sRes;
        try {
            List<String> sResList = namedParameterJdbcTemplate.query(sSql, new ParameterizedRowMapper<String>() {
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString(1);
                }
            });
            if (sResList == null || sResList.size() < 1) {
                return "";
            }
            sRes = sResList.get(0);
        } catch (BadSqlGrammarException ex) {
            ex.printStackTrace();
            logger.error("算法sql有误，信息为{}",ex.getMessage());
            return "0";
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("异常错误，信息为{}",ex.getMessage());
            return "0";
        }
        if (sRes == null) {
            return "";
        }
        return sRes;
    }

    private String getParamStr(Map<String, Object> objMap, String paramName) {
        Object obj = objMap.get(paramName);
        if (obj == null) {
            logger.info("param:" + paramName + " Should Be Setted!");
            return "";
        } else if (obj instanceof Date) {
            return DateUtil.getDateStr((Date) obj, "yyyy-MM-dd");
        } else {
            return obj.toString();
        }
    }
}
