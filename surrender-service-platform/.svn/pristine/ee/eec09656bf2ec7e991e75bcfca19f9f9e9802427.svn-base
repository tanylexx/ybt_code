package com.sinosoft.surrender.addprem.bo.impl;

import com.sinosoft.lis.specedor.dto.req.LisSpecEdorInfoDTO;
import com.sinosoft.lis.specedor.dto.req.LisSpecEdorReqDTO;
import com.sinosoft.lis.specedor.dto.res.LisSpecEdorResDTO;
import com.sinosoft.lis.specedor.service.LisIAEdorSpecService;
import com.sinosoft.surrender.addprem.bo.AddPremContImportCoreBO;
import com.sinosoft.surrender.addprem.dao.AddPremDao;
import com.sinosoft.surrender.addprem.dto.AddPremImportDto;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.db.model.LcPol;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class AddPremContImportCoreBOImpl implements AddPremContImportCoreBO {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LockBO lockBO;
    @Resource
    private LisIAEdorSpecService lisIAEdorSpecService;
    @Autowired
    private AddPremDao addPremDao;
    @Autowired
    private LcPolSpecDAO lcPolSpecDAO;


    @Override
    public LisSpecEdorResDTO callWebService(AddPremImportDto addPremImportDto) {
        return lisIAEdorSpecService.delIASpecEdor(generateLisSpecEdorReqDTO(addPremImportDto));
    }

    @Override
    public void modifyImportCoreProcessStatus(Map<String, Object> map) {
        addPremDao.modifyImportCoreProcessStatus(map);
    }


    private LisSpecEdorReqDTO generateLisSpecEdorReqDTO(AddPremImportDto addPremImportDto) {
        LisSpecEdorReqDTO lisSpecEdorReqDTO = new LisSpecEdorReqDTO();
        lisSpecEdorReqDTO.setBankCode(addPremImportDto.getBankCode());
        lisSpecEdorReqDTO.setBankDate(addPremImportDto.getTransDate());
        lisSpecEdorReqDTO.setBrNo(addPremImportDto.getBrNo());
        lisSpecEdorReqDTO.setContno(addPremImportDto.getContNo());
        lisSpecEdorReqDTO.setEdorInfoDTO(generateEdorInfoDto(addPremImportDto));
        lisSpecEdorReqDTO.setEdorType("IA");
        lisSpecEdorReqDTO.setFunctionFlag("26");
        List<LcPol> lcPols = lcPolSpecDAO.getLcPolByContNo(addPremImportDto.getContNo());
        lisSpecEdorReqDTO.setRiskCode(CollectionUtils.isNotEmpty(lcPols)?lcPols.get(0).getRiskcode():"0000");
        lisSpecEdorReqDTO.setTransrNo(addPremImportDto.getTransNo());
        lisSpecEdorReqDTO.setZoneNo(addPremImportDto.getZoneNo());
        lisSpecEdorReqDTO.setEdorAcceptNo(addPremImportDto.getEdorAcceptNo());
        return lisSpecEdorReqDTO;
    }

    private LisSpecEdorInfoDTO generateEdorInfoDto(AddPremImportDto addPremImportDto) {
        LisSpecEdorInfoDTO edorInfoDTO =new LisSpecEdorInfoDTO();
        edorInfoDTO.setAddAmnt(addPremImportDto.getAmnt());
        edorInfoDTO.setEdorAppDate(addPremImportDto.getApplyDate());
        edorInfoDTO.setEdorPrtNo(addPremImportDto.getEdorPrtNo());
        edorInfoDTO.setPrem(addPremImportDto.getPrem());
        edorInfoDTO.setRemainInsuYear(addPremImportDto.getRemainInsuyear());
        edorInfoDTO.setEdorValiDate(addPremImportDto.getcValidate());
        //电商流水号用于幂等校验
        edorInfoDTO.setSurrenderId(String.valueOf(System.currentTimeMillis()));
        edorInfoDTO.setPayInTv(new BigDecimal(0));
        return edorInfoDTO;
    }
}
